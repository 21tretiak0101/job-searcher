package by.ttre16.example.service;

import by.ttre16.example.model.Vacancy;
import java.util.List;

public interface VacancyService {

    List<Vacancy> findVacancies(String website, String city, String technology);

}
