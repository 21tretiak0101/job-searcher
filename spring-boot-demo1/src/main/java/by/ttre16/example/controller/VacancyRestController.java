package by.ttre16.example.controller;

import by.ttre16.example.model.Vacancy;
import by.ttre16.example.service.VacancyService;
import by.ttre16.example.service.VacancyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jdevby")
@CrossOrigin("http://localhost:4200")
public class VacancyRestController {

    private VacancyService service;

    @Autowired
    public VacancyRestController(VacancyServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/vacancies")
    public ResponseEntity<List<Vacancy>> getVacancies(
            @RequestParam(defaultValue = "belmeta") String website,
            @RequestParam(defaultValue = "Минск") String city,
            @RequestParam(defaultValue = "") String technology){

        List<Vacancy> vacancies = service.findVacancies(website, city, technology);

        return vacancies == null
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(vacancies, HttpStatus.OK);

    }
}

