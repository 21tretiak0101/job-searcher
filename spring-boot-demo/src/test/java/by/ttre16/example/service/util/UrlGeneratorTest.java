package by.ttre16.example.service.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UrlGeneratorTest {

    private final static String BASE_URL = "https://google.com";

    @Test
    void queryString() {
        String key = "key", value1 = "value1", value2 = "value2";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL);

        assertThrows(IllegalArgumentException.class, () -> setNullKey(builder));

        setNullBuilder(key, value1, value2);
        assertEquals(builder.build().toString(), BASE_URL);

        setNullValues(builder, key);
        assertEquals(builder.build().toString(), BASE_URL);

        setAll(builder, key, value1, value2);
        assertEquals(builder.build().toString(), String.format("%s?%s=%s+%s", BASE_URL, key, value1, value2));
    }

    void setNullKey(UriComponentsBuilder builder) {
        UrlGenerator.queryString(builder, null);
    }

    void setNullValues(UriComponentsBuilder builder, String key) {
        UrlGenerator.queryString(builder, key);
    }

    void setNullBuilder(String key, Object ... values) {
        UrlGenerator.queryString(null, key, values);
    }

    void setAll(UriComponentsBuilder builder, String key, Object ... values) {
        UrlGenerator.queryString(builder, key, values);
    }
}
