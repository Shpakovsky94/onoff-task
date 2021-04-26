package com.example.onofftask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onofftask.exception.InvalidInputException;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.HttpRequestResolver;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
class ParsingServiceImplTest {

    ParsingServiceImpl target;

    @Mock
    HttpRequestResolver httpRequestResolver;
    @Mock
    HttpServletRequest  req;
    @Mock
    RestTemplate  restTemplate;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        restTemplate = mock(RestTemplate.class);
        httpRequestResolver = mock(HttpRequestResolver.class);
        target = new ParsingServiceImpl(httpRequestResolver, restTemplate);
        ReflectionTestUtils.setField(target, "bitfineUrlTickers", "http://foo");
        ReflectionTestUtils.setField(target, "bitfineUrlSymbols", "http://foo");
    }

    @Test
    void validateParamsAndReturnCryptoTest() throws InvalidInputException, InvalidNameException {
        List<String> valueList = new ArrayList<>();
        valueList.add("BTC");

        HttpHeaders  headers      = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "PostmanRuntime/7.26.8");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        when(httpRequestResolver.getParam("name", req)).thenReturn("BTC");
        when(httpRequestResolver.getDoubleParam("amount", req)).thenReturn(5.2);
        when(httpRequestResolver.getParam("wallet", req)).thenReturn("mock-wallet");
        when(restTemplate.exchange("http://foo", HttpMethod.GET, entity, String.class)).thenReturn(ResponseEntity.ok("btc,eth"));


        Crypto result = target.validateParamsAndReturnCrypto(req);
        Assertions.assertNotNull(result);

    }

    @Test
    void getCurrentMarketPriceFromApiTest() {
        HttpHeaders  headers      = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "PostmanRuntime/7.26.8");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        when(restTemplate.exchange("http://foo?symbols=tBTC", HttpMethod.GET, entity, String.class)).thenReturn(ResponseEntity.ok("BTC,53889"));

        BigDecimal result = target.getCurrentMarketPriceFromApi("BTC");
        Assertions.assertNotNull(result);
    }

    @Test
    void parseDataFromJsonToArrayTest() {
        HttpHeaders  headers      = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "PostmanRuntime/7.26.8");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        when(restTemplate.exchange("http://foo?symbols=tBTC", HttpMethod.GET, entity, String.class)).thenReturn(ResponseEntity.ok("BTC,10.2"));

        List<String> result = target.parseDataFromJsonToArray("BTC", false);
        assertEquals(2, result.size());

    }
}