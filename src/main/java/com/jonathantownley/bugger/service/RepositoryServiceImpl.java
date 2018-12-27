package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.dao.json.JsonDao;
import com.jonathantownley.bugger.model.Repository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService{

    @Autowired
    private JsonDao jsonDao;

    private String fileName = Bugger.repositoriesFileName;
    private List<Repository> repositories;
    private List<String> repositoryNames;

    @Override
    public List<Repository> getRepositories() {
        readJsonData();
        return repositories;
    }

    @Override
    public List<String> getRepositoryNames() {
        readJsonData();
        return repositoryNames;
    }

    @Override
    public Repository getRepository(String repositoryName) {
        readJsonData();
        return repositories.stream().filter(r -> r.getName().equals(repositoryName)).findFirst().get();
    }

    @Override
    public void addRepository(Repository repository) {
        repositories.add(repository);
        writeJsonData();
    }


    // Private methods
    private void readJsonData() {
        try {
            JSONObject jsonObject = (JSONObject) jsonDao.read(fileName);
            JSONArray jsonRepos = (JSONArray) jsonObject.get("repository");
            repositories = new ArrayList<>();
            repositoryNames = new ArrayList<>();

            for (int ii=0; ii<jsonRepos.size(); ii++) {
                JSONObject repoObject = (JSONObject) jsonRepos.get(ii);
                String name = repoObject.get("name").toString();
                String databaseFileLocation = repoObject.get("databaseFileLocation").toString();

                repositories.add(new Repository(name, databaseFileLocation));
                repositoryNames.add(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeJsonData() {
        // Simple JSON's toJSONString() method doesn't work very well,
        // especially with file paths, so form the string manually
        String jsonString = String.format("{%n  \"repository\": [%n");

        for (int ii=0; ii<repositories.size(); ii++) {
            Repository repo = repositories.get(ii);
            jsonString = String.format("%s    {%n",jsonString);
            jsonString = String.format("%s      \"name\": \"%s\",%n",jsonString,repo.getName());
            jsonString = String.format("%s      \"databaseFileLocation\": \"%s\"%n",jsonString,repo.getDatabaseFileLocation());
            if (ii < repositories.size()-1) {
                jsonString = String.format("%s    },%n", jsonString);
            }
            else {
                jsonString = String.format("%s    }%n", jsonString);
            }
        }
        jsonString = String.format("%s  ]%n}",jsonString);
        jsonDao.write(fileName, jsonString);
    }
}
