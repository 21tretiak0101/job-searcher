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
public class BelmetaStrategy extends AbstractStrategy {

    private final static String BELMETA_BASE_URL = "https://belmeta.com";
    private final static String BELMETA_VACANCIES_GET = "vacansii";
    private final static String FIRST_PARAM_PAGE = "page";
    private final static String SECOND_PARAM_QUERY = "q";

    @Override
    protected String generatePageUrl(int pageNumber, Object... queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BELMETA_BASE_URL)
                .path(BELMETA_VACANCIES_GET)
                .queryParam(FIRST_PARAM_PAGE, pageNumber);
        UrlGenerator.queryString(builder, SECOND_PARAM_QUERY, queryParams);
        String url = builder.build().toString();
        log.info(url);
        return url;
    }

    @Override
    protected Elements getPageVacancies(Document page) {
        return page.getElementsByClass("job no-logo");
    }

    @Override
    protected String parseTitle(Element vacancy) {
        return vacancy.getElementsByClass("title")
                .first()
                .getElementsByTag("a")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseSalary(Element vacancy) {
        Element salary = vacancy.getElementsByClass("job-data salary").first();
        return salary == null
                ? null
                : salary.text().trim();
    }

    @Override
    protected String parseAddress(Element vacancy) {
        return vacancy.getElementsByClass("job-data region")
                .first()
                .attr("title")
                .trim();
    }

    @Override
    protected String parseCompanyName(Element vacancy) {
        return vacancy.getElementsByClass("job-data company")
                .first()
                .text()
                .trim();
    }

    @Override
    protected String parseUrl(Element vacancy) {
        return BELMETA_BASE_URL + vacancy.getElementsByClass("title")
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
    protected String parseDescription(Element vacancy) {
        return vacancy.getElementsByClass("desc")
                .first()
                .text()
                .trim();
    }
}
