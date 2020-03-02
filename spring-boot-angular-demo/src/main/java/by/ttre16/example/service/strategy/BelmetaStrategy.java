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

public class BelmetaStrategy implements Strategy {

    private VacancyDto vacancyDto;
    private static List<Vacancy> vacancies;
    private static final String BASE_URL = "https://belmeta.om";
    /**
     * 1. %s - Technology name
     * 2. %s - City
     * 3. %s - Page number
     */
    private static final String BASE_PARAMS = "/vacansii?q=Junior+%s&l=%s&page=%d";


    @Override
    public List<Vacancy> getVacancies() throws IOException {

        vacancies = new ArrayList<>();

        for (int pageNumber = 1; true; pageNumber++) {
            Elements vacanciesFromPage = getDocument(pageNumber).getElementsByClass("job no-logo");

            if(vacanciesFromPage.size() == 0) break;

            putAllVacanciesFromPage(vacanciesFromPage);
        }

        return vacancies;
    }

    @Override
    public Document getDocument(int pageNumber) throws IOException {
        String technology = vacancyDto.getTechnology();
        String city = vacancyDto.getCity();

        String URL = BASE_URL + String.format(BASE_PARAMS, technology, city, pageNumber);
        Connection connection = Jsoup.connect(URL);
        return connection.get();
    }

    @Override
    public void putAllVacanciesFromPage(Elements vacanciesFromPage){
        for(Element vacancyDetails: vacanciesFromPage){

            String title = parseTitle(vacancyDetails);
            if(!title.contains("Junior"))
                continue;

            Vacancy vacancy = Vacancy.builder()
                    .title(title)
                    .salary(parseSalary(vacancyDetails))
                    .city(parseCity(vacancyDetails))
                    .companyName(parseCompanyName(vacancyDetails))
                    .url(parseUrl(vacancyDetails))
                    .build();

            vacancies.add(vacancy);
        }
    }

    public void setVacancyDto(VacancyDto vacancyDto) {
        this.vacancyDto = vacancyDto;
    }


    @Override
    public String parseTitle(Element element) {
        return element.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .text()
                .trim();
    }

    @Override
    public String parseSalary(Element element) {
        Element salaryElement = element.getElementsByClass("job-data salary").first();
        if(salaryElement == null){
            return "Зарплата не указана.";
        }

        return salaryElement.text().trim();
    }

    @Override
    public String parseCity(Element element) {
        return element.getElementsByClass("job-data region")
                .first()
                .attr("title")
                .trim();
    }

    @Override
    public String parseCompanyName(Element element) {
        return element.getElementsByClass("job-data company")
                .first()
                .text()
                .trim();
    }

    @Override
    public String parseUrl(Element element) {
        return BASE_URL + element.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .attr("href")
                .trim();
    }

}
