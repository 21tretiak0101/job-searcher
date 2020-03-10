package by.ttre16.example.service.dateformat;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class MyDateFormat {

    private static final String MY_TEMPLATE = "dd MMMM";

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };

    public static String getDate(String date, String dateTemplate){
        SimpleDateFormat localDateTemplate = new SimpleDateFormat(dateTemplate, myDateFormatSymbols);

        try {
            Date localDate = localDateTemplate.parse(date);
            return new SimpleDateFormat(MY_TEMPLATE, myDateFormatSymbols).format(localDate);

        } catch (ParseException e) {
            log.warn("Date parsing exception with message: {}", e.getMessage());
        }

        return date;
    }
}
