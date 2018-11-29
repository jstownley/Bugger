import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    // Hold a reusable reference to the Repository SessionFactory
    private static final SessionFactory repoSessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        // Create a StandardServiceRegistery
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        // Create database for test repos (only need to do this once)
//        save(new Repository("testRepo1",
//            "Test repository 1",
//            "./data/testRepo1/testRepo1.mv.db"));
//
//        save(new Repository("testRepo2",
//            "Test repository 2",
//            "./data/testRepo2/testRepo2.mv.db"));

        // Get list of repositories
        List<Repository> repositories = fetchAllRepositories();
        Map<String, Repository> repositoryMap = new HashMap<>();

        for (Repository repo : repositories) {
            repositoryMap.put(repo.getName(),repo);

            System.out.println("Repo name: " + repo.getName());
            System.out.println("Repo description: " + repo.getDescription());
            System.out.println("Repo location: " + repo.getDatabaseFileLocation());
            System.out.println("");
        }
    }

    private static Long save(Repository repository){
        // Open a session
        Session session = repoSessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        Long id = (Long) session.save(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();

        return id;
    }

    private static List<Repository> fetchAllRepositories() {
        // Open session
        Session session = repoSessionFactory.openSession();

        // Get repositories
        List<Repository> repositories = session.createQuery("select r from Repository r").getResultList();

        // Close session
        session.close();

        return repositories;
    }

    private static Repository findRepositoryById(Long id) {
        // Open a session
        Session session = repoSessionFactory.openSession();

        // Retrieve a persistent object
        Repository repository = session.get(Repository.class, id);

        // Close session
        session.close();

        return repository;
    }

    private static void update(Repository repository) {
        // Open a session
        Session session = repoSessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        session.update(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    private static void delete(Repository repository) {
        // Open a session
        Session session = repoSessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        session.delete(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }
}
