package by.ttre16.example.controller;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import by.ttre16.example.service.Strategy;
import by.ttre16.example.service.StrategyProvider;
import by.ttre16.example.service.VacancyService;
import jdk.internal.module.ServicesCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyRestController {

    public VacancyService service;

    @Autowired
    public VacancyRestController(VacancyService service, StrategyProvider provider) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Vacancy>> getVacancies(
            @RequestBody VacancyDto vacancyDto){

        List<Vacancy> vacancies = service.getVacancies(vacancyDto);

        return ResponseEntity.ok(vacancies);
    }

}
