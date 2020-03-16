package by.ttre16.example.controller;

import by.ttre16.example.exception.StrategyExistsException;
import by.ttre16.example.model.Vacancy;
import by.ttre16.example.service.VacancyService;
import by.ttre16.example.service.VacancyServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Slf4j
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
            @RequestParam(defaultValue = "belmeta", required = false) String website,
            @RequestParam(defaultValue = "Минск", required = false) String city,
            @RequestParam(defaultValue = "", required = false) String technology) throws IOException, StrategyExistsException {

        log.info("GET request with params: website - {}, city - {}, technology - {}", website, city, technology);

        List<Vacancy> vacancies = service.findVacancies(website, city, technology);

        if(vacancies == null) throw new StrategyExistsException(website + " website strategy doesn't exist.");

        return new ResponseEntity<>(vacancies, HttpStatus.OK);

    }

    @ExceptionHandler({IOException.class, StrategyExistsException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public HashMap<String, String> accessError(Exception e){
        log.error(e.getMessage());
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}

