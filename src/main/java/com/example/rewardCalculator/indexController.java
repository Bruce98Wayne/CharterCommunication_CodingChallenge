package com.example.rewardCalculator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class indexController {

    private static Integer totalRewards = 0;

    @SuppressWarnings("unchecked")
    public String calculateRewards()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/static/data/trasnsactionData.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            HashMap<String, Integer> mappedMonthlyRewards = new HashMap<>();

            employeeList.forEach( emp -> parseTransactionObject( (JSONObject) emp , mappedMonthlyRewards ));
            System.out.println(mappedMonthlyRewards);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("Total Rewards: %d!", totalRewards);
    }

    private static void parseTransactionObject(JSONObject employee, HashMap<String, Integer> map)
    {
        //Get employee object within list
        JSONObject transactionObject = (JSONObject) employee;
        Integer rewardForEachTransaction = 0;
        Integer monthlyRewardAccumulator = 0;

        //Get employee first name
        String month = (String) transactionObject.get("month");
        ArrayList<Integer> transactions = new ArrayList<Integer>();
        JSONArray jArray = (JSONArray) transactionObject.get("transactions");
        if (jArray != null) {
            for (int i = 0; i < jArray.size(); i++) {
                Integer transaction = (Integer.parseInt(jArray.get(i).toString()));
                transactions.add(transaction);
                rewardForEachTransaction = calculateRewardForEachTransaction(transaction);
                monthlyRewardAccumulator += rewardForEachTransaction;
            }
        }

        totalRewards += monthlyRewardAccumulator;

        map.put(month, monthlyRewardAccumulator);
        System.out.println(totalRewards);
    }

    @GetMapping("/")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws Exception {

        int totalRewards = 0;
        return calculateRewards();

    }

    private static Integer calculateRewardForEachTransaction(Integer transactionAmount) {
        Integer reward = 0;

        if(transactionAmount > 50 && transactionAmount < 100) {
            reward += transactionAmount - 50;
        } else  if (transactionAmount >= 100) {
            reward += 2 * (transactionAmount - 100) + 50;
        }

        return reward;
    }
}
