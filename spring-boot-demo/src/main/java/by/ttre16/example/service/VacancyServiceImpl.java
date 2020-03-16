package by.ttre16.example.service;

import by.ttre16.example.model.Vacancy;
import by.ttre16.example.service.strategy.AbstractStrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class VacancyServiceImpl implements VacancyService{

    private StrategyProvider provider;

    @Autowired
    public VacancyServiceImpl(StrategyProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<Vacancy> findVacancies(String website, String city, String technology) throws IOException {
        AbstractStrategy strategy = provider.getStrategy(website);
        if (strategy == null) return null;

        strategy.setCity(city);
        strategy.setTechnology(technology);
        return strategy.getVacancies();
    }
}

