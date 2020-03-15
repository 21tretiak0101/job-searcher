package by.ttre16.example.service.strategy;

import by.ttre16.example.service.dateformat.MyDateFormat;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Slf4j
@NoArgsConstructor
@Component
public class BelmetaStrategy extends AbstractStrategy {
    /**
     * @params
     * 1. Junior + %s + %s means that the parameter q consists of three values (Junior, city, technology)
     *
     * 2. %d - page number value
     */
    protected final String URL_TEMPLATE = "https://belmeta.com/vacansii?q=Junior+%s+%s&page=%d";
    protected final String BASE_URL = "https://belmeta.com";


    @Override
    protected Elements getPageVacancies(Document page) {
        return page.getElementsByClass("job no-logo");
    }

    @Override
    protected String getStrategyUrl() {
        return URL_TEMPLATE;
    }

    @Override
    protected String parseVacancyTitle(Element vacancy) {
        return vacancy.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseVacancySalary(Element vacancy) {
        Element salary = vacancy.getElementsByClass("job-data salary").first();
        return salary == null
                ? null
                : salary.text().trim();
    }

    @Override
    protected String parseVacancyAddress(Element vacancy) {
        return vacancy.getElementsByClass("job-data region")
                .first()
                .attr("title")
                .trim();
    }

    @Override
    protected String parseVacancyCompanyName(Element vacancy) {
        return vacancy.getElementsByClass("job-data company")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseVacancyUrl(Element vacancy) {
        return BASE_URL + vacancy.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .attr("href")
                .trim();
    }

    @Override
    protected String parseDate(Element vacancy) {
        String date = vacancy.getElementsByAttribute("data-value")
                .first()
                .attr("data-value")
                .trim();

        return MyDateFormat.getDate(date, "dd.MM.yyyy HH:mm:ss");
    }

    @Override
    protected String parseVacancyText(Element vacancy) {
        return vacancy.getElementsByClass("desc")
                .first()
                .text()
                .trim();
    }
}
