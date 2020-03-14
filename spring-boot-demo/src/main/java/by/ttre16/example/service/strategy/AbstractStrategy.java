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

@Setter
public abstract class AbstractStrategy {

    private static List<Vacancy> vacancies;
    private String city;
    private String technology;

    public List<Vacancy> getVacancies() throws IOException {
        return getVacanciesFromAllPages();
    }

    private List<Vacancy>  getVacanciesFromAllPages() throws IOException {
        vacancies = new ArrayList<>();

        for (int pageNumber = 0; true; pageNumber++) {
            Elements pageVacancies = getPageVacancies(getPage(pageNumber));

            if(pageVacancies.size() == 0)
                return vacancies;

            putAllVacanciesFromPage(pageVacancies);
        }
    }

    private Document getPage(int pageNumber) throws IOException {
        String pageURL = String.format(getStrategyUrl(), technology, city, pageNumber);
        Connection connection = Jsoup.connect(pageURL);
        return connection.get();
    }

    private void putAllVacanciesFromPage(Elements pageVacancies){
        for(Element vacancy: pageVacancies){
            if (!isCorrectVacancy(vacancy))
                continue;

            vacancies.add(parseVacancy(vacancy));
        }
    }

    private Vacancy parseVacancy(Element vacancy){
        return  Vacancy.builder()
                .title(parseVacancyTitle(vacancy))
                .salary(parseVacancySalary(vacancy))
                .text(parseVacancyText(vacancy))
                .address(parseVacancyAddress(vacancy))
                .companyName(parseVacancyCompanyName(vacancy))
                .url(parseVacancyUrl(vacancy))
                .date(parseDate(vacancy))
                .build();
    }

    private boolean isCorrectVacancy(Element vacancy){
        String vacancyTitle = parseVacancyTitle(vacancy);
        return vacancyTitle.contains("Junior") && vacancyTitle.contains(technology);
    }

    abstract String getStrategyUrl();

    abstract String parseVacancyTitle(Element vacancy);

    abstract String parseVacancySalary(Element vacancy);

    abstract String parseVacancyText(Element vacancy);

    abstract String parseVacancyAddress(Element vacancy);

    abstract String parseVacancyCompanyName(Element vacancy);

    abstract String parseVacancyUrl(Element vacancy);

    abstract String parseDate(Element vacancy);

    abstract Elements getPageVacancies(Document page);
}
