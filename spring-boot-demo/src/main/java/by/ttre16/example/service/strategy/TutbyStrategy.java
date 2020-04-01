package by.ttre16.example.service.strategy;

import by.ttre16.example.service.util.MyDateFormat;
import by.ttre16.example.service.util.UrlGenerator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@NoArgsConstructor
@Component
public class TutbyStrategy extends AbstractStrategy {

    private final static String TUT_BY_BASE_URL = "https://jobs.tut.by";
    private final static String TUT_BY_VACANCIES_GET = "search/vacancy";
    private final static String FIRST_PARAM_PAGE = "page";
    private final static String SECOND_PARAM_TEXT = "text";

    @Override
    protected String generatePageUrl(int pageNumber, Object... queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(TUT_BY_BASE_URL)
                .path(TUT_BY_VACANCIES_GET)
                .queryParam(FIRST_PARAM_PAGE, pageNumber);
        UrlGenerator.queryString(builder, SECOND_PARAM_TEXT, queryParams);
        String url = builder.build().toString();
        log.info(url);
        return url;

    }

    @Override
    protected Elements getPageVacancies(Document page) {
        return page.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
    }

    @Override
    protected String parseTitle(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseSalary(Element vacancy) {
        Element salary = vacancy
                .getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-compensation")
                .first();

        return salary == null
                ? null
                : salary.text().trim();
    }

    @Override
    protected String parseAddress(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseCompanyName(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseUrl(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title")
                .first()
                .attr("href")
                .trim();
    }

    @Override
    protected String parseDate(Element vacancy) {
        String date = vacancy.getElementsByClass("vacancy-serp-item__publication-date")
                .first()
                .text()
                .trim();
        return MyDateFormat.getDate(date, "dd MMMM");
    }

    @Override
    protected String parseDescription(Element vacancy) {
        return vacancy.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_requirement")
                .first()
                .text()
                .trim();
    }
}
