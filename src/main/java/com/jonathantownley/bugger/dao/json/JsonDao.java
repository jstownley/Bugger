package com.jonathantownley.bugger.dao.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Repository
public class JsonDao {

    public Object read(String jsonFileName) throws Exception {
        FileReader reader = new FileReader(jsonFileName);
        return new JSONParser().parse(reader);
    }

    public void write(String jsonFileName, String jsonString) {
        try(FileWriter file = new FileWriter(jsonFileName)) {
            file.write(jsonString);
            file.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
