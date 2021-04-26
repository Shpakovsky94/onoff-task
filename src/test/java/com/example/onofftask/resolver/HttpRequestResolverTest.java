package com.example.onofftask.resolver;

import static org.junit.jupiter.api.Assertions.*;

import com.example.onofftask.exception.InvalidInputException;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class HttpRequestResolverTest {

    HttpRequestResolver target = new HttpRequestResolver();

    @Mock
    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    @Test
    void getParamTest() throws InvalidInputException {
        Mockito.when(req.getParameter("name")).thenReturn("mock-name");
        String result = target.getParam("name", req);

        Assertions.assertEquals("mock-name", result);
    }

    @Test
    void getDoubleParamTest() throws InvalidInputException {
        Mockito.when(req.getParameter("name")).thenReturn("10.2");
        Double result = target.getDoubleParam("name", req);

        Assertions.assertEquals(10.2, result);
    }
}