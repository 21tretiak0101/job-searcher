package by.ttre16.example.service.strategy;

import by.ttre16.example.service.dateformat.MyDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
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
            return null;
        }
        return salaryElement.text().trim();
    }

    @Override
    protected String parseAddress(Element element) {
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

    @Override
    protected String parseDate(Element element) {
        String date = element.getElementsByAttribute("data-value")
                .first()
                .attr("data-value")
                .trim();

        return MyDateFormat.getDate(date, "dd.MM.yyyy HH:mm:ss");
    }

    @Override
    protected String parseText(Element element) {
        return element.getElementsByClass("desc")
                .first()
                .text()
                .trim();
    }
}
