package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.dao.json.JsonDao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    private String fileName = Bugger.preferencesFileName;
    private String author = null;
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeJsonData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", author);
        jsonObject.put("showClosed", showClosed);
        jsonObject.put("showRejected", showRejected);
        jsonObject.put("showDuplicates", showDuplicates);
        jsonDao.write(fileName, jsonObject.toJSONString());
    }
}
