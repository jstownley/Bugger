package com.jonathantownley.bugger.config;

import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.service.RepositoryService;
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
@ComponentScan(basePackages = "com.jonathantownley.bugger")
public class ConfigBeans {

    @Autowired
    private RepositoryService repositoryService;

    @Bean
    @Lazy(value = true)
    public Map<String, SessionFactory> sessionFactory() {
        Map<String, SessionFactory> sessionFactories = new TreeMap<>();
        List<Repository> repositories = repositoryService.getRepositories();

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
}
