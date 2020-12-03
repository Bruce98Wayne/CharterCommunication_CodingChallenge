package com.example.rewardCalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    public String calculateRerads(String name) {
        return String.format("Hello %s!", name);
        
    }


    @GetMapping("/calculateRewards")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return calculateRerads(name);

    }
}
