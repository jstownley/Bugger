import com.jonathantownley.bugger.model.*;
import com.jonathantownley.bugger.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Application {

    // Hold reusable references to the various bug-related services
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BugService bugService = new BugServiceImpl();
    private static final NoteService noteService = new NoteServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final SeverityService severityService = new SeverityServiceImpl();
    private static final StatusService statusService = new StatusServiceImpl();

    public static void main(String[] args) {
        // Get repositories and sessionFactories
        List<Repository> repositories = loadRepositories();
        createTestDatabasesIfTheyDontExist(repositories);

    }

    private static void createTestDatabasesIfTheyDontExist(List<Repository> repositories) {
        // Create repo config info if there aren't any repos configured
        if(repositories == null) {
            JSONObject repoDetails = new JSONObject();
            JSONObject repoObject = new JSONObject();
            JSONArray repoList = new JSONArray();
            
            // Test Repo 1
            repoDetails.put("name","testRepo1");
            repoDetails.put("databaseFileLocation","./data/testRepo1");
            repoObject.put("repository", repoDetails);
            repoList.add(repoObject);
            
            // Test Repo 2
            repoDetails.put("name","testRepo2");
            repoDetails.put("databaseFileLocation","./data/testRepo2");
            repoObject.put("repository", repoDetails);
            repoList.add(repoObject);
            
            try(FileWriter file = new FileWriter("./src/main/resources/repositories.json")) {
                file.write(repoList.toJSONString());
                file.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
            repositories = loadRepositories();
        }

        for(Repository repo : repositories)
        {
            if(authorService.findById(repo,1L) == null) {

                // Init authors, products, and stages
                authorService.update(repo, new Author("Brinkley"));
                authorService.update(repo, new Author("Cooper"));
                authorService.update(repo, new Author("Reed"));
                authorService.update(repo, new Author("Townley"));

                productService.update(repo, new Product("QuickLook","Runs pre-defined analyses for a single test event"));
                productService.update(repo, new Product("DataMorpher","Translates data from one format to another"));
                productService.update(repo, new Product("GenericMergePlotter","Mereges data from multiple test events before analyzing"));

                severityService.update(repo, new Severity("Critical"));
                severityService.update(repo, new Severity("Major"));
                severityService.update(repo, new Severity("Minor"));
                severityService.update(repo, new Severity("Cosmetic"));

                statusService.update(repo, new Status("Open"));
                statusService.update(repo, new Status("Examine"));
                statusService.update(repo, new Status("Implement"));
                statusService.update(repo, new Status("Verify"));
                statusService.update(repo, new Status("Closed"));
                statusService.update(repo, new Status("Rejected"));
                statusService.update(repo, new Status("Duplicate"));
            }
        }
    }

    private static List<Repository> loadRepositories() {
        List<Repository> repositories = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("./src/main/resources/repositories.json")) {
            JSONArray jsonRepos = (JSONArray) jsonParser.parse(reader);

            
            for (Object repo : jsonRepos) {
                JSONObject repoObject = (JSONObject) ((JSONObject) repo).get("repository");
                String name = repoObject.get("name").toString();
                String databaseFileLocation = repoObject.get("databaseFileLocation").toString();
                
                repositories.add(new Repository(name, 
                    databaseFileLocation,
                    createSessionFactory(databaseFileLocation + "/" + name)));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return repositories;
    }

    private static SessionFactory createSessionFactory(String dbUrl) {
        Configuration cfg = new Configuration();
        cfg.configure("repoBase_hibernate.cfg.xml");
        cfg.getProperties().setProperty("hibernate.connection.url","jdbc:h2:" + dbUrl);

        return cfg.buildSessionFactory();
    }
}
