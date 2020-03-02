package by.ttre16.example.service.strategy;

import by.ttre16.example.DocumentConnectionException;
import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public interface Strategy {

    void putAllVacanciesFromPage(Elements vacanciesFromPage);

    Document getDocument(int pageNumber) throws IOException;

    void setVacancyDto(VacancyDto vacancyDto);

    List<Vacancy> getVacancies() throws IOException;

    String parseTitle(Element vacancyDetails);

    String parseSalary(Element vacancyDetails);

    String parseCity(Element vacancyDetails);

    String parseCompanyName(Element vacancyDetails);

    String parseUrl(Element vacancyDetails);

}
