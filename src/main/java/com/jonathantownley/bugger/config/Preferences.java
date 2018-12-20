package com.jonathantownley.bugger.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preferences {

    private String author;
    private boolean showClosed;
    private boolean showRejected;
    private boolean showDuplicates;
    private String preferencesFileName;

    public Preferences(String preferencesFileName) {
        this.preferencesFileName = preferencesFileName;
        readPreferencesFromFile();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        writePreferencesToFile();
    }

    public boolean isShowClosed() {
        return showClosed;
    }

    public void setShowClosed(boolean showClosed) {
        this.showClosed = showClosed;
        writePreferencesToFile();
    }

    public boolean isShowRejected() {
        return showRejected;
    }

    public void setShowRejected(boolean showRejected) {
        this.showRejected = showRejected;
        writePreferencesToFile();
    }

    public boolean isShowDuplicates() {
        return showDuplicates;
    }

    public void setShowDuplicates(boolean showDuplicates) {
        this.showDuplicates = showDuplicates;
        writePreferencesToFile();
    }

    public String getPreferencesFileName() {
        return preferencesFileName;
    }

    public void setPreferencesFileName(String preferencesFileName) {
        this.preferencesFileName = preferencesFileName;
    }

    private void readPreferencesFromFile() {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(preferencesFileName)) {
            JSONObject jsonPref = (JSONObject) jsonParser.parse(reader);
            author = (String) jsonPref.get("author");
            showClosed = (boolean) jsonPref.get("showClosed");
            showRejected = (boolean) jsonPref.get("showRejected");
            showDuplicates = (boolean) jsonPref.get("showDuplicates");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean writePreferencesToFile() {
        JSONObject prefObject = new JSONObject();
        prefObject.put("author", author);
        prefObject.put("showClosed", showClosed);
        prefObject.put("showRejected", showRejected);
        prefObject.put("showDuplicates", showDuplicates);

        try(FileWriter file = new FileWriter(preferencesFileName)) {
            file.write(prefObject.toJSONString());
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
