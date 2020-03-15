package by.ttre16.example.service;

import by.ttre16.example.service.strategy.AbstractStrategy;
import by.ttre16.example.service.strategy.BelmetaStrategy;
import by.ttre16.example.service.strategy.TutbyStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class StrategyProvider {

    private static HashMap<String, AbstractStrategy> vacancyMap = new HashMap<>();
    private BelmetaStrategy belmetaStrategy;
    private TutbyStrategy tutbyStrategy;

    @Autowired
    public StrategyProvider(BelmetaStrategy belmetaStrategy, TutbyStrategy tutbyStrategy) {
        this.belmetaStrategy = belmetaStrategy;
        this.tutbyStrategy = tutbyStrategy;
    }

    @PostConstruct
    public void init(){
        vacancyMap.put("belmeta", belmetaStrategy);
        vacancyMap.put("tutby", tutbyStrategy);
    }

    public AbstractStrategy getStrategy(String website){
        return vacancyMap.get(website);
    }

}
