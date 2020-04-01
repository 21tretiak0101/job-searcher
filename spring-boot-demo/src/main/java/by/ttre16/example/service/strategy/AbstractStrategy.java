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

    private static final String POSITION = "Middle";
    private static List<Vacancy> vacancies;
    private String city;
    private String technology;

    abstract String generatePageUrl(int pageNumber, Object... queryParams);

    abstract Elements getPageVacancies(Document page);

    abstract String parseTitle(Element vacancy);

    abstract String parseSalary(Element vacancy);

    abstract String parseDescription(Element vacancy);

    abstract String parseAddress(Element vacancy);

    abstract String parseCompanyName(Element vacancy);

    abstract String parseUrl(Element vacancy);

    abstract String parseDate(Element vacancy);


    public List<Vacancy> getVacancies() throws IOException {
        return getVacanciesFromAllPages();
    }

    private List<Vacancy>  getVacanciesFromAllPages() throws IOException {
        vacancies = new ArrayList<>();
        for (int pageNumber = 0; true; pageNumber++) {
            Elements pageVacancies = getPageVacancies(getPage(pageNumber));
            if(pageVacancies.size() == 0) return vacancies;
            putAllVacanciesFromPage(pageVacancies);
        }
    }

    private Document getPage(int pageNumber) throws IOException {
        String pageURL = generatePageUrl(pageNumber, POSITION, city, technology);
        Connection connection = Jsoup.connect(pageURL);
        return connection.get();
    }

    private void putAllVacanciesFromPage(Elements pageVacancies){
        for(Element vacancy: pageVacancies){
            if (!isCorrectVacancy(vacancy)) continue;
            vacancies.add(parseVacancy(vacancy));
        }
    }

    private Vacancy parseVacancy(Element vacancy){
        return  Vacancy.builder()
                .title(parseTitle(vacancy))
                .salary(parseSalary(vacancy))
                .description(parseDescription(vacancy))
                .address(parseAddress(vacancy))
                .companyName(parseCompanyName(vacancy))
                .url(parseUrl(vacancy))
                .date(parseDate(vacancy))
                .build();
    }

    private boolean isCorrectVacancy(Element vacancy){
        String vacancyTitle = parseTitle(vacancy);
        return vacancyTitle.contains(POSITION) && vacancyTitle.contains(technology);
    }
}
