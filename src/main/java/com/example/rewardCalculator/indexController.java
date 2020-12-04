package com.example.rewardCalculator;

import net.minidev.json.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import java.io.FileNotFoundException;

@RestController
public class indexController {

    public static Object readTransactionData(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    public String calculateRewards(String name, int totalRewards) {
//        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("example.json");
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("age"));
//        readTransactionData("../../resources/static/data/trasnsactionData.json")
        return String.format("Hello %s!", name);

    }


    @GetMapping("/calculateRewards")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {

        int totalRewards = 0;
        return calculateRewards(name, totalRewards);


    }
}
