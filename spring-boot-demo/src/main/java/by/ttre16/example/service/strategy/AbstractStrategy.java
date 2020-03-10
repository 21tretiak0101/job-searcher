package by.ttre16.example.service.strategy;

import by.ttre16.example.model.Vacancy;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStrategy {

    private static List<Vacancy> vacancies;
    @Setter
    private String city;
    @Setter
    private String technology;

    public List<Vacancy> getVacancies() throws IOException {
        vacancies = new ArrayList<>();

        for (int pageNumber = 0; true; pageNumber++) {
            Elements pageVacancies = getPageVacancyTemplate(getDocument(pageNumber));

            if(pageVacancies.size() == 0)
                return vacancies;

            putAllVacanciesFromPage(pageVacancies);
        }
    }

    private Document getDocument(int pageNumber) throws IOException {
        String UrlTemplate = getUrlTemplate();

        String pageURL = String.format(UrlTemplate, technology, city, pageNumber);
        Connection connection = Jsoup.connect(pageURL);
        return connection.get();
    }

    private void putAllVacanciesFromPage(Elements template){
        for(Element vacancyDetails: template){
            if (!isCorrectVacancy(parseTitle(vacancyDetails)))
                continue;

            Vacancy vacancy = buildVacancy(vacancyDetails);
            vacancies.add(vacancy);
        }
    }

    private Vacancy buildVacancy(Element details){
        return  Vacancy.builder()
                .title(parseTitle(details))
                .salary(parseSalary(details))
                .text(parseText(details))
                .address(parseAddress(details))
                .companyName(parseCompanyName(details))
                .vacancyURL(parseUrl(details))
                .date(parseDate(details))
                .build();
    }

    private boolean isCorrectVacancy(String vacancyTitle){
        if(technology.equals(""))
            return vacancyTitle.contains("Junior");

        return vacancyTitle.contains("Junior") && vacancyTitle.contains(technology);
    }


    abstract String getUrlTemplate();

    abstract String parseTitle(Element element);

    abstract String parseSalary(Element element);

    abstract String parseText(Element details);

    abstract String parseAddress(Element element);

    abstract String parseCompanyName(Element element);

    abstract String parseUrl(Element element);

    abstract Elements getPageVacancyTemplate(Document document);

    abstract String parseDate(Element element);

    //todo
/*
1. первым параметром нужно принимать стратегию
2. сделать стратегию обязательным параметром
 */




}
