package by.ttre16.example.controller;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import by.ttre16.example.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@CrossOrigin("http://localhost:4200")
public class VacancyRestController {

    public VacancyService service;

    @Autowired
    public VacancyRestController(VacancyService service) {
        this.service = service;
    }

    @GetMapping("/{city}/{technology}/{website}")
    public ResponseEntity<List<Vacancy>> getVacancies(
            @PathVariable String city,
            @PathVariable String technology,
            @PathVariable String website){


        VacancyDto vacancy = VacancyDto.builder()
                .city(city)
                .website(website)
                .technology(technology)
                .build();

        System.out.println(vacancy);

        List<Vacancy> vacancies = service.getVacancies(vacancy);
        if (vacancies == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vacancies, HttpStatus.OK);
    }
}

