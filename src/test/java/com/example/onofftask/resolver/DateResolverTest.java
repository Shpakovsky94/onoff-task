package com.example.onofftask.resolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DateResolverTest {

    DateResolver target = new DateResolver();

    @Test
    void truncateTimeTest_with_null() {
        String  result = target.truncateTime(null);
        Assertions.assertEquals("", result);
    }

    @Test
    void truncateTimeTest_with_incorrectInput() {
        Date date = new Date(1L);
        String  result = target.truncateTime(date);
        Assertions.assertEquals("1970-01-01 03:00", result);
    }
}