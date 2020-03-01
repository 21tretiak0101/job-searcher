package by.ttre16.example.service.strategy;


import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import by.ttre16.example.service.Strategy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BelmetaStrategy implements Strategy {
    /**
     * 1. s - Technology name
     * 2. s - City name
     */
    private static final String baseURL = "https://belmeta.com/vacansii?q=Junior+%s&l=%s";

    private VacancyDto vacancyDto;

    private static Connection connection;


    public Document getDocument() throws IOException {
        String technology = vacancyDto.getTechnology();
        String city = vacancyDto.getCity();

        String URL = String.format(baseURL, technology, city);
        Connection connection = Jsoup.connect(URL);
        return connection.get();
    }


    public void setVacancyDto(VacancyDto vacancyDto) {
        this.vacancyDto = vacancyDto;
    }

    @Override
    public List<Vacancy> getVacancies() {
        return null;
    }

    @Override
    public String parseTitle() {
        return null;
    }

    @Override
    public String parseSalary() {
        return null;
    }

    @Override
    public String parseCity() {
        return null;
    }

    @Override
    public String parseCompanyName() {
        return null;
    }

    @Override
    public String parseSiteName() {
        return null;
    }

    @Override
    public String parseUrl() {
        return null;
    }
}
