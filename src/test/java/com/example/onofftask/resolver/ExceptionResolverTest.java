package com.example.onofftask.resolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ExceptionResolverTest {

    ExceptionResolver target = new ExceptionResolver();

    @Test
    void handleExceptionTest() {

        Map<String, Object> result = target.handleException(new NullPointerException());
        Assertions.assertTrue(result.containsKey("NullPointerException"));
    }
}