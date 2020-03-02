package by.ttre16.example.service;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import by.ttre16.example.service.strategy.AbstractStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class VacancyService {

    private StrategyProvider provider;

    @Autowired
    public VacancyService(StrategyProvider provider) {
        this.provider = provider;
    }

    public List<Vacancy> getVacancies(VacancyDto vacancyDto){

        String website = vacancyDto.getSiteName();
        AbstractStrategy strategy = provider.getStrategy(website);
        strategy.setVacancyDto(vacancyDto);

        List<Vacancy> vacancies = null;

        try {
            vacancies = strategy.getVacancies();
        }
        catch (IOException e){
            log.error("URL: connection not established with message {}", e.getMessage());
        }

        return vacancies;
    }
}
