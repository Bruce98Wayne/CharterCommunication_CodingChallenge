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

    @GetMapping("/calculateRewards")
    public String requestHandler() throws Exception {
        return calculateRewards();
    }

    @SuppressWarnings("unchecked")
    // This function parses the JSON object and call the subsequent functions to compute rewards:
    public String calculateRewards()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/static/data/trasnsactionData.json")) {
            //Read JSON file
            Object transactionObject = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) transactionObject;

            HashMap<String, Integer> mappedMonthlyRewards = new HashMap<>();

            transactionList.forEach( emp -> parseTransactionObject( (JSONObject) emp , mappedMonthlyRewards ));
            System.out.println(mappedMonthlyRewards);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Total Rewads are: " + totalRewards.toString());

        return String.format("Total Rewards: %d! \n \nCheck console for monthly rewards!", totalRewards);
    }

    private static void parseTransactionObject(JSONObject transactionObject, HashMap<String, Integer> map)
    {
        Integer rewardForEachTransaction = 0;
        Integer monthlyRewardAccumulator = 0;

        //Get month and transaction from transactionObject
        String month = (String) transactionObject.get("month");
        ArrayList<Integer> transactions = new ArrayList<Integer>();
        JSONArray transactionJsonArray = (JSONArray) transactionObject.get("transactions");

        if (transactionJsonArray != null) {
            for (int i = 0; i < transactionJsonArray.size(); i++) {
                Integer transaction = (Integer.parseInt(transactionJsonArray.get(i).toString()));
                transactions.add(transaction);
                rewardForEachTransaction = calculateRewardForEachTransaction(transaction);
                monthlyRewardAccumulator += rewardForEachTransaction;
            }
        }

        totalRewards += monthlyRewardAccumulator;
        map.put(month, monthlyRewardAccumulator);

        System.out.println("The rewards for the month " + month + " is " + monthlyRewardAccumulator.toString());
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
