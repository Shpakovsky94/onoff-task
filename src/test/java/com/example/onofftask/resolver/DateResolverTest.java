package com.example.onofftask.resolver;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DateResolverTest {

    DateResolver dateResolver = new DateResolver();

    @Test
    void truncateTimeTest_with_null() {
        String result = dateResolver.truncateTime(null);
        Assertions.assertEquals("", result);
    }

    @Test
    void truncateTimeTest_with_incorrectInput() {
        Date   date   = new Date(1L);
        String result = dateResolver.truncateTime(date);
        Assertions.assertEquals("1970-01-01 02:00", result);
    }
}