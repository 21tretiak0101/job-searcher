package by.ttre16.example.service.util;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class UrlGenerator {

    public static void queryString(UriComponentsBuilder builder, String key, Object ... values){
        Assert.notNull(key, "Name must not be null");
        if(!ObjectUtils.isEmpty(builder) && !ObjectUtils.isEmpty(values)) {
            StringBuilder textValue = new StringBuilder();
            Arrays.stream(values).forEach(q -> textValue.append(q).append("+"));
            textValue.deleteCharAt(textValue.lastIndexOf("+"));
            builder.queryParam(key, textValue);
        }
    }
}
