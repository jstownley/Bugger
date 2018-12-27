package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.dao.json.JsonDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    private String fileName = Bugger.settingsFileName;
    private String author = null;
    private List<String> statuses;
    private List<String> severities;
    private boolean showClosed = false;
    private boolean showRejected = false;
    private boolean showDuplicates = false;

    @Autowired
    private JsonDao jsonDao;

    @Override
    public String getAuthor() {
        readJsonData();
        return author;
    }

    @Override
    public Boolean getShowClosed() {
        readJsonData();
        return showClosed;
    }

    @Override
    public Boolean getShowRejected() {
        readJsonData();
        return showRejected;
    }

    @Override
    public Boolean getShowDuplicates() {
        readJsonData();
        return showDuplicates;
    }

    @Override
    public List<String> getStatuses() {
        readJsonData();
        return statuses;
    }

    @Override
    public List<String> getSeverities() {
        readJsonData();
        return severities;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
        writeJsonData();
    }

    @Override
    public void setShowClosed(boolean showClosed) {
        this.showClosed = showClosed;
        writeJsonData();
    }

    @Override
    public void setShowRejected(boolean showRejected) {
        this.showRejected = showRejected;
        writeJsonData();
    }

    @Override
    public void setShowDuplicates(boolean showDuplicates) {
        this.showDuplicates = showDuplicates;
        writeJsonData();
    }


    // Private methods
    private void readJsonData() {
        try {
            JSONObject jsonObject = (JSONObject) jsonDao.read(fileName);
            author = (String) jsonObject.get("author");
            showClosed = (boolean) jsonObject.get("showClosed");
            showRejected = (boolean) jsonObject.get("showRejected");
            showDuplicates = (boolean) jsonObject.get("showDuplicates");
            statuses = new ArrayList<>((JSONArray) jsonObject.get("status"));
            severities = new ArrayList<>((JSONArray) jsonObject.get("severity"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeJsonData() {
        // Simple JSON's toJSONString() method doesn't work very well,
        // especially with file paths, so form the string manually
        String jsonString = String.format("{%n");

        jsonString = String.format("%s  \"author\": \"%s\",%n", jsonString, author);
        jsonString = String.format("%s  \"showClosed\": %s,%n", jsonString, showClosed);
        jsonString = String.format("%s  \"showRejected\": %s,%n", jsonString, showRejected);
        jsonString = String.format("%s  \"showDuplicates\": %s,%n", jsonString, showDuplicates);

        jsonString = String.format("%s  \"status\": [%n", jsonString);
        for (int ii=0; ii<statuses.size(); ii++) {
            if (ii < statuses.size()-1) {
                jsonString = String.format("%s    \"%s\",%n", jsonString, statuses.get(ii));
            }
            else {
                jsonString = String.format("%s    \"%s\"%n", jsonString, statuses.get(ii));
            }
        }
        jsonString = String.format("%s  ],%n", jsonString);

        jsonString = String.format("%s  \"severity\": [%n", jsonString);
        for (int ii=0; ii<severities.size(); ii++) {
            if (ii < severities.size()-1) {
                jsonString = String.format("%s    \"%s\",%n", jsonString, severities.get(ii));
            }
            else {
                jsonString = String.format("%s    \"%s\"%n", jsonString, severities.get(ii));
            }
        }
        jsonString = String.format("%s  ]%n", jsonString);

        jsonString = String.format("%s}%n",jsonString);
        jsonDao.write(fileName, jsonString);
    }
}
