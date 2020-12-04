package com.example.rewardCalculator;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class indexController {

//    public static Object readJsonSimpleDemo(String filename) throws Exception {
//        FileReader reader = new FileReader(filename);
//        JSONParser jsonParser = new JSONParser();
//        return jsonParser.parse(reader);
//    }
//
//    public String calculateRewards(String name, int totalRewards) throws Exception {
//        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("C:\\Users\\Karthik.P\\Downloads\\CharterCommunication_CodingChallenge-main\\src\\main\\resources\\static\\data\\trasnsactionData.json");
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("age"));
//        //readJsonSimpleDemo("../../resources/static/data/trasnsactionData.json");
//        return String.format("Hello %s!", name);
//
//    }

    @SuppressWarnings("unchecked")
    public String calculateRewards(String name, int totalRewards)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/static/data/trasnsactionData.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("Hello %s!", name);
    }


    @GetMapping("/calculateRewards")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws Exception {

        int totalRewards = 0;
        return calculateRewards(name, totalRewards);

    }
}
