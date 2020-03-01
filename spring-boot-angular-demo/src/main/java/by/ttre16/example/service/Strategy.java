package by.ttre16.example.service;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface Strategy {

    Document getDocument() throws IOException;

    void setVacancyDto(VacancyDto vacancyDto);

    List<Vacancy> getVacancies();

    String parseTitle();

    String parseSalary();

    String parseCity();

    String parseCompanyName();

    String parseSiteName();

    String parseUrl();

}
