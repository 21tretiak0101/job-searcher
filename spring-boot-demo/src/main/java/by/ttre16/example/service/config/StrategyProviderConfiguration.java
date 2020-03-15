package by.ttre16.example.service.config;

import by.ttre16.example.service.strategy.AbstractStrategy;
import by.ttre16.example.service.strategy.BelmetaStrategy;
import by.ttre16.example.service.strategy.TutbyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class StrategyProviderConfiguration {

    private TutbyStrategy tutbyStrategy;
    private BelmetaStrategy belmetaStrategy;

    @Autowired
    public StrategyProviderConfiguration(TutbyStrategy tutbyStrategy, BelmetaStrategy belmetaStrategy) {
        this.tutbyStrategy = tutbyStrategy;
        this.belmetaStrategy = belmetaStrategy;
    }

    @Bean
    public HashMap<String, AbstractStrategy> getVacancyMap(){
        HashMap<String, AbstractStrategy> vacancyMap = new HashMap<>();

        vacancyMap.put("belmeta", belmetaStrategy);
        vacancyMap.put("tutby", tutbyStrategy);

        return vacancyMap;
    }
}
