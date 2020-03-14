package by.ttre16.example.service.strategy;

import by.ttre16.example.service.dateformat.MyDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class TutbyStrategy extends AbstractStrategy {
    /**
     * @params
     * 1. Junior + %s + %s means that the parameter q consists of three values (Junior, city, technology)
     *
     * 2. %d - page number value
     */
    protected final String URL_TEMPLATE = "https://jobs.tut.by/search/vacancy?text=Junior+%s+%s&page=%d";


    @Override
    protected Elements getPageVacancies(Document page) {
        return page.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
    }

    @Override
    protected String getStrategyURL() {
        return URL_TEMPLATE;
    }

    @Override
    protected String parseVacancyTitle(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseVacancySalary(Element vacancy) {
        Element salary = vacancy
                .getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation").first();

        return salary == null
                ? null
                : salary.text().trim();
    }

    @Override
    protected String parseVacancyAddress(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseVacancyCompanyName(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseVacancyUrl(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .attr("href")
                .trim();
    }

    @Override
    protected String parseDate(Element vacancy) {
        String date = vacancy.getElementsByClass("vacancy-serp-item__publication-date")
                .first()
                .text().trim();

        return MyDateFormat.getDate(date, "dd MMMM");
    }

    @Override
    protected String parseVacancyText(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_requirement")
                .first()
                .text()
                .trim();
    }
}