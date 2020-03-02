package by.ttre16.example.service;

import by.ttre16.example.service.strategy.BelmetaStrategy;
import by.ttre16.example.service.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StrategyProvider {
    private static HashMap<String, Strategy> vacancyMap = new HashMap<>();

    static {
        vacancyMap.put("belmeta", new BelmetaStrategy());
    }

    public Strategy getStrategy(String website){
        return vacancyMap.get(website);
    }
}
