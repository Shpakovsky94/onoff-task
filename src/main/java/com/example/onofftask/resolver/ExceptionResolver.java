package com.example.onofftask.resolver;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    public Map<String, Object> handleException(Exception e) {
        log.error(e.getClass().getSimpleName(), e);

        Map<String, Object> result = new HashMap<>();
        result.put(e.getClass().getSimpleName(), e.getMessage());

        return result;
    }
}
