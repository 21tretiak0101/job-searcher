package by.ttre16.example.service.strategy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TutbyStrategy extends AbstractStrategy {

    protected final String URL_TEMPLATE = "https://jobs.tut.by/search/vacancy?text=%sJunior+%s&page=%d";
    protected final String BASE_URL = "https://jobs.tut.by";

    @Override
    String getUrlTemplate() {
        return URL_TEMPLATE;
    }

    @Override
    String parseTitle(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .text()
                .trim();
    }

    @Override
    String parseSalary(Element element) {
        Element salaryElement = element
                .getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation").first();

        if(salaryElement == null){
            return "Зарплата не указана.";
        }

        return salaryElement.text().trim();
    }

    @Override
    String parseCity(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address")
                .first()
                .text()
                .trim();
    }

    @Override
    String parseCompanyName(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer")
                .first()
                .text()
                .trim();
    }

    @Override
    String parseUrl(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .attr("href")
                .trim();
    }

    @Override
    Elements getPageVacancyTemplate(Document document) {
        return document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
    }
}