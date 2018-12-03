import com.jonathantownley.bugger.config.ConfigData;
import com.jonathantownley.bugger.config.Preferences;
import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.service.BugService;
import com.jonathantownley.bugger.service.BugServiceImpl;
import com.jonathantownley.bugger.service.ProductService;
import com.jonathantownley.bugger.service.ProductServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {

    // Hold reusable references to the various bug-related services
    private static final BugService bugService = new BugServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();

    public static void main(String[] args) {
        // Get config data and preferences
        ConfigData configData = new ConfigData("./src/main/resources/config.json");
        Preferences preferences = new Preferences("./src/main/resources/preferences.json");

        // Get repositories and sessionFactories
        List<Repository> repositories = loadRepositories();
        createTestDatabasesIfTheyDontExist(repositories, configData, preferences);

    }

    private static void createTestDatabasesIfTheyDontExist(List<Repository> repositories,
                                                           ConfigData configData,
                                                           Preferences preferences) {
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
            if(productService.findById(repo,1L) == null) {

                // Init products
                productService.update(repo, new Product("QuickLook","Runs pre-defined analyses for a single test event"));
                productService.update(repo, new Product("DataMorpher","Translates data from one format to another"));
                productService.update(repo, new Product("GenericMergePlotter","Mereges data from multiple test events before analyzing"));

                // Create a couple of bugs
                Bug bug = new Bug(
                    new Date(),
                    preferences.getAuthor(),
                    "Make it more awesome",
                    "The tool needs to be much more awesome than it already is",
                    repo.getName(),
                    productService.findById(repo, 1L),
                    new ArrayList<Note>(),
                    configData.getSeverities().get(0),
                    configData.getStatuses().get(0)
                );
                bugService.update(repo, bug);
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
