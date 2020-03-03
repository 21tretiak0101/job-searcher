package by.ttre16.example.service.strategy;

import by.ttre16.example.service.strategy.dateformat.MyDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class TutbyStrategy extends AbstractStrategy {
    /**
     * @params
     * 1. %s - technology
     * 2. %s - city
     * 3. %s - page
     */
    protected final String URL_TEMPLATE = "https://jobs.tut.by/search/vacancy?text=%s+Junior+%s&page=%d";

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
            return "Зарплата не указана.";
        }

        return salaryElement.text().trim();
    }

    @Override
    protected String parseCity(Element element) {
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
}