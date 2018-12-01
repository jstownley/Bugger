import com.jonathantownley.bugger.model.*;
import com.jonathantownley.bugger.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.*;

public class Application {

    // Hold reusable references to the Repository SessionFactory and services
    private static final SessionFactory repoSessionFactory = buildSessionFactory();
    private static final AuthorService authorService = new AuthorServiceImpl();
    public static final BugService bugService = new BugServiceImpl();
    public static final NoteService noteService = new NoteServiceImpl();
    public static final ProductService productService = new ProductServiceImpl();
    public static final RepositoryService repositoryService = new RepositoryServiceImpl();
    public static final SeverityService severityService = new SeverityServiceImpl();
    public static final StatusService statusService = new StatusServiceImpl();

    private static SessionFactory buildSessionFactory(){
        // Create a StandardServiceRegistery
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        // Get repositories and sessionFactories
        Map<String, Repository> repositoryMap = loadRepositories();
//        Map<String,SessionFactory> sessionFactories = loadRepoSessionFactories(repositoryMap);
        Map<String,SessionFactory> sessionFactories = createTestDatabasesIfTheyDontExist();

    }

    public static Map<String,SessionFactory> createTestDatabasesIfTheyDontExist() {
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
                authorService.update(sf, new Author("Brinkley"));
                authorService.update(sf, new Author("Cooper"));
                authorService.update(sf, new Author("Reed"));
                authorService.update(sf, new Author("Townley"));

                productService.update(sf, new Product("QuickLook","Runs pre-defined analyses for a single test event"));
                productService.update(sf, new Product("DataMorpher","Translates data from one format to another"));
                productService.update(sf, new Product("GenericMergePlotter","Mereges data from multiple test events before analyzing"));

                severityService.update(sf, new Severity("Critical"));
                severityService.update(sf, new Severity("Major"));
                severityService.update(sf, new Severity("Minor"));
                severityService.update(sf, new Severity("Cosmetic"));

                statusService.update(sf, new Status("Open"));
                statusService.update(sf, new Status("Examine"));
                statusService.update(sf, new Status("Implement"));
                statusService.update(sf, new Status("Verify"));
                statusService.update(sf, new Status("Closed"));
                statusService.update(sf, new Status("Rejected"));
                statusService.update(sf, new Status("Duplicate"));
            }
        }

        return sessionFactories;
    }

    private static Map<String, SessionFactory> loadRepoSessionFactories(Map<String, Repository> repositoryMap) {
        Map<String, SessionFactory> sessionFactories = new TreeMap<>();

        for (Map.Entry<String, Repository> repo : repositoryMap.entrySet()) {
            sessionFactories.put(repo.getKey(),
                createSessionFactory(repo.getValue().getDatabaseFileLocation()));
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

    private static SessionFactory createSessionFactory(String dbUrl) {

        Configuration cfg = new Configuration();
        cfg.configure("repoBase_hibernate.cfg.xml");
        cfg.getProperties().setProperty("hibernate.connection.url","jdbc:h2:" + dbUrl);
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(Bug.class);
        cfg.addAnnotatedClass(Note.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Severity.class);
        cfg.addAnnotatedClass(Status.class);

        return cfg.buildSessionFactory();
    }
}
