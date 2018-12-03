package com.jonathantownley.bugger.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigData {

    private List<String> statuses;
    private List<String> severities;
    private String configFileName;

    public ConfigData(String configFileName) {
        this.configFileName = configFileName;
        readConfigDataFromFile();
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public void setSeverities(List<String> severities) {
        this.severities = severities;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public List<String> getSeverities() {
        return severities;
    }

    public void readConfigDataFromFile() {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(configFileName)) {
            JSONObject jsonConfig = (JSONObject) jsonParser.parse(reader);
            statuses = new ArrayList<>((JSONArray) jsonConfig.get("status"));
            severities = new ArrayList<>((JSONArray) jsonConfig.get("severity"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean writeConfigDataToFile() {
        JSONObject configObject = new JSONObject();
        JSONArray configList = new JSONArray();

        configList.addAll(statuses);
        configObject.put("status",configList);

        configList.clear();
        configList.addAll(severities);
        configObject.put("severity",configList);

        try(FileWriter file = new FileWriter(configFileName)) {
            file.write(configObject.toJSONString());
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
