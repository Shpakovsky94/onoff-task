package com.example.onofftask.resolver;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ExceptionResolverTest {

    ExceptionResolver exceptionResolver = new ExceptionResolver();

    @Test
    void handleExceptionTest() {

        Map<String, Object> result = exceptionResolver.handleException(new NullPointerException());
        Assertions.assertTrue(result.containsKey("NullPointerException"));
    }
}