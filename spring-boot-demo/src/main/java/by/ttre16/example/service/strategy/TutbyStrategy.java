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
    protected String getUrlTemplate() {
        return URL_TEMPLATE;
    }

    @Override
    protected String parseTitle(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseSalary(Element element) {
        Element salaryElement = element
                .getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation").first();

        if(salaryElement == null){
            return null;
        }

        return salaryElement.text().trim();
    }

    @Override
    protected String parseAddress(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseCompanyName(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseUrl(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .attr("href")
                .trim();
    }

    @Override
    protected Elements getPageVacancyTemplate(Document document) {
        return document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
    }

    @Override
    protected String parseDate(Element element) {
        String date = element.getElementsByClass("vacancy-serp-item__publication-date")
                .first()
                .text().trim();

        return MyDateFormat.getDate(date, "dd MMMM");
    }

    @Override
    protected String parseText(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_requirement")
                .first()
                .text()
                .trim();
    }
}