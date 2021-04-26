package com.example.onofftask.resolver;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateResolver {
    private static final Logger log = LoggerFactory.getLogger(DateResolver.class);

    public String truncateTime(Date date) {

        String result = "";
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }
}
