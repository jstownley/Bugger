import com.jonathantownley.bugger.model.*;
import com.jonathantownley.bugger.service.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.util.*;

public class Application {

    // Hold reusable references to the Repository SessionFactory and services
    private static final SessionFactory repoSessionFactory = buildSessionFactory();
    private static final AuthorService authorService = new AuthorServiceImpl();
    public static final BugService bugService = new BugServiceImpl();
    public static final NoteService noteService = new NoteServiceImpl();
    public static final ProductService productService = new ProductServiceImpl();
    public static final RepositoryService repositoryService = new RepositoryServiceImpl();
    public static final StageService stageService = new StageServiceImpl();

    private static SessionFactory buildSessionFactory(){
        // Create a StandardServiceRegistery
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        createTestDatabasesIfTheyDontExist();

        Map<String, Repository> repositoryMap = new TreeMap<>();
        Map<String,SessionFactory> sessionFactories = new TreeMap<>();

    }

    public static void createTestDatabasesIfTheyDontExist() {
        // Define test database info
        List<Repository> testRepos = new ArrayList<>();
        testRepos.add(new Repository("testRepo1",
            "Test repository 1",
            "./data/testRepo1/testRepo1"));
        testRepos.add(new Repository("testRepo2",
            "Test repository 2",
            "./data/testRepo2/testRepo2"));

        // Create repo database
        if(repositoryService.findById(repoSessionFactory, 1L) == null) {
            repositoryService.update(repoSessionFactory,testRepos.get(0));
            repositoryService.update(repoSessionFactory,testRepos.get(1));
        }

        // If the test repos don't exist, create those databases
        Map<String, Repository> repositoryMap = loadRepositories();
        Map<String,SessionFactory> sessionFactories = loadRepoSessionFactories(repositoryMap);

        for(Repository repo : testRepos)
        {
            SessionFactory sf = sessionFactories.get(repo.getName());
            if(authorService.findById(sf,1L) == null) {

                // Init authors, products, and stages
                authorService.update(sf, new Author("Me","Who","Mehoo"));
                authorService.update(sf, new Author("Me","Too","Me2"));

                productService.update(sf, new Product("Tool1","A tool to do one thing"));
                productService.update(sf, new Product("Tool2","A tool to do somethine else"));
                productService.update(sf, new Product("Tool3","A tool to do yet another thing"));

                stageService.update(sf, new Stage("Open"));
                stageService.update(sf, new Stage("Implement"));
                stageService.update(sf, new Stage("Verify"));
                stageService.update(sf, new Stage("Closed"));
            }
        }
    }

    private static Map<String, SessionFactory> loadRepoSessionFactories(Map<String, Repository> repositoryMap) {
        Map<String, SessionFactory> sessionFactories = new TreeMap<>();

        for (Map.Entry<String, Repository> repo : repositoryMap.entrySet()) {

            Configuration cfg = new Configuration();
            cfg.configure("repoBase_hibernate.cfg.xml");
            cfg.getProperties().setProperty("hibernate.connection.url","jdbc:h2:" +
                repo.getValue().getDatabaseFileLocation());
            cfg.addAnnotatedClass(Author.class);
            cfg.addAnnotatedClass(Bug.class);
            cfg.addAnnotatedClass(Note.class);
            cfg.addAnnotatedClass(Product.class);
            cfg.addAnnotatedClass(Stage.class);

            sessionFactories.put(repo.getKey(),cfg.buildSessionFactory());

        }

        return sessionFactories;
    }

    private static Map<String, Repository> loadRepositories() {
        Map<String, Repository> repositoryMap = new TreeMap<>();

        List<Repository> repositories = repositoryService.findAll(repoSessionFactory);

        for (Repository repo : repositories) {
            repositoryMap.put(repo.getName(), repo);
        }

        return repositoryMap;
    }
}
