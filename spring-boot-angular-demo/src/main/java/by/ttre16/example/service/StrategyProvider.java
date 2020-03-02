package by.ttre16.example.service;

import by.ttre16.example.service.strategy.AbstractStrategy;
import by.ttre16.example.service.strategy.BelmetaStrategy;
import by.ttre16.example.service.strategy.TutbyStrategy;
import org.springframework.stereotype.Component;


import java.util.HashMap;

@Component
public class StrategyProvider {
    private static HashMap<String, AbstractStrategy> vacancyMap = new HashMap<>();

    static {
        vacancyMap.put("Belmeta", new BelmetaStrategy());
        vacancyMap.put("TUT.BY", new TutbyStrategy());
    }

    public AbstractStrategy getStrategy(String website){
        return vacancyMap.get(website);
    }
}
