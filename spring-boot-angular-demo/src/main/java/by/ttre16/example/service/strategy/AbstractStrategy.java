package by.ttre16.example.service.strategy;

import by.ttre16.example.domain.Vacancy;
import by.ttre16.example.dto.VacancyDto;
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
    private VacancyDto vacancyDto;


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
        String technology = vacancyDto.getTechnology();
        String city = vacancyDto.getCity();
        String UrlTemplate = getUrlTemplate();

        String pageURL = String.format(UrlTemplate, technology, city, pageNumber);
        Connection connection = Jsoup.connect(pageURL);
        return connection.get();
    }



    private void putAllVacanciesFromPage(Elements template){
        for(Element vacancyDetails: template){
            if (!isCorrectVacancy(parseTitle(vacancyDetails))) continue;

            Vacancy vacancy = buildVacancy(vacancyDetails);
            vacancies.add(vacancy);
        }
    }

    private Vacancy buildVacancy(Element details){
        return  Vacancy.builder()
                .title(parseTitle(details))
                .salary(parseSalary(details))
                .city(parseCity(details))
                .companyName(parseCompanyName(details))
                .url(parseUrl(details))
                .build();
    }

    public void setVacancyDto(VacancyDto vacancyDto) {
        this.vacancyDto = vacancyDto;
    }

    private boolean isCorrectVacancy(String vacancyTitle){
        return vacancyTitle.contains("Junior");
    }

    abstract String getUrlTemplate();

    abstract String parseTitle(Element element);

    abstract String parseSalary(Element element);

    abstract String parseCity(Element element);

    abstract String parseCompanyName(Element element);

    abstract String parseUrl(Element element);

    abstract Elements getPageVacancyTemplate(Document document);

}
