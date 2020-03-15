package by.ttre16.example.service;

import by.ttre16.example.service.strategy.AbstractStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StrategyProvider {

    private HashMap<String, AbstractStrategy> vacancyMap;

    @Autowired
    public StrategyProvider(HashMap<String, AbstractStrategy> vacancyMap) {
        this.vacancyMap = vacancyMap;
    }

    public AbstractStrategy getStrategy(String website){
        return vacancyMap.get(website);
    }

}
