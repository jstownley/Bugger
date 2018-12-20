package com.jonathantownley.bugger.config;

import com.jonathantownley.bugger.model.Repository;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.*;
import java.util.*;

@Configuration
public class ConfigBeans {

    @Bean
    @Lazy(value = true)
    public Map<String, SessionFactory> sessionFactory(List<Repository> repositories) {
        Map<String, SessionFactory> sessionFactories = new TreeMap<>();

        for (int rr=0; rr<repositories.size(); rr++) {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            File cfgFile = new File("./src/main/resources/xml/repoBase_hibernate.cfg.xml");
            cfg.configure(cfgFile);
            cfg.getProperties().setProperty("hibernate.connection.url","jdbc:h2:" +
                repositories.get(rr).getDatabaseFileLocation());
            sessionFactories.put(repositories.get(rr).getName(), cfg.buildSessionFactory());
        }

        return sessionFactories;
    }

    @Bean
    public List<Repository> loadRepositories() {
        List<Repository> repositories = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("./src/main/resources/json/repositories.json")) {
            JSONArray jsonRepos = (JSONArray) jsonParser.parse(reader);


            for (Object repo : jsonRepos) {
                JSONObject repoObject = (JSONObject) ((JSONObject) repo).get("repository");
                String name = repoObject.get("name").toString();
                String databaseFileLocation = repoObject.get("databaseFileLocation").toString();

                repositories.add(new Repository(name, databaseFileLocation));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return repositories;
    }

    @Bean
    public List<String> repositoryNameList(List<Repository> repositories) {
        List<String> repoNames = new ArrayList<>();

        for (Repository repository : repositories) {
            repoNames.add(repository.getName());
        }

        return  repoNames;
    }

    @Bean
    public Preferences getPreferences() {
        return new Preferences("./src/main/resources/json/preferences.json");
    }
}
