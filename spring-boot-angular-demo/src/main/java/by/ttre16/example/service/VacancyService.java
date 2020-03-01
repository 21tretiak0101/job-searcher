package by.ttre16.example.service;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    private StrategyProvider strategyProvider;

    public VacancyService(StrategyProvider strategyProvider) {
        this.strategyProvider = strategyProvider;
    }

    public List<Vacancy> getVacancies(VacancyDto vacancyDto) {

        String website = vacancyDto.getSiteName();
        Strategy strategy = strategyProvider.getStrategy(website);
        strategy.setVacancyDto(vacancyDto);

        return strategy.getVacancies();
    }
}
