package by.ttre16.example.service.strategy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BelmetaStrategy extends AbstractStrategy {

    protected final String URL_TEMPLATE = "https://belmeta.com/vacansii?q=Junior+%s&l=%s&page=%d";
    protected final String BASE_URL = "https://belmeta.com";

    @Override
    protected String parseTitle(Element element) {
        return element.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseSalary(Element element) {
        Element salaryElement = element.getElementsByClass("job-data salary").first();
        if(salaryElement == null){
            return "Зарплата не указана.";
        }
        return salaryElement.text().trim();
    }

    @Override
    protected String parseCity(Element element) {
        return element.getElementsByClass("job-data region")
                .first()
                .attr("title")
                .trim();
    }

    @Override
    protected String parseCompanyName(Element element) {
        return element.getElementsByClass("job-data company")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseUrl(Element element) {
        return BASE_URL + element.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .attr("href")
                .trim();
    }


    @Override
    protected String getUrlTemplate() {
        return URL_TEMPLATE;
    }

    @Override
    protected Elements getPageVacancyTemplate(Document document) {
        return document.getElementsByClass("job no-logo");
    }
}
