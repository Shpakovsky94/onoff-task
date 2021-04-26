package com.example.onofftask.controller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.exception.InvalidInputException;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.ExceptionResolver;
import com.example.onofftask.service.CryptoServiceHelper;
import com.example.onofftask.service.ParsingService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CryptoControllerTest {

    CryptoController target;

    @Mock
    HttpServletRequest  req;
    @Mock
    CryptoServiceHelper cryptoServiceHelper;
    @Mock
    ExceptionResolver   exceptionResolver;
    @Mock
    ParsingService      parsingService;

    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        cryptoServiceHelper = mock(CryptoServiceHelper.class);
        exceptionResolver = mock(ExceptionResolver.class);
        parsingService = mock(ParsingService.class);
        target = new CryptoController(cryptoServiceHelper, exceptionResolver, parsingService);
    }

    @Test
    void getAllTest() {
        CryptoDto       dto1          = new CryptoDto();
        CryptoDto       dto2          = new CryptoDto();
        CryptoDto       dto3          = new CryptoDto();
        List<CryptoDto> cryptoDtoList = new ArrayList<>();
        cryptoDtoList.add(dto1);
        cryptoDtoList.add(dto2);
        cryptoDtoList.add(dto3);
        when(cryptoServiceHelper.findAll()).thenReturn(cryptoDtoList);

        List<CryptoDto> result = target.getAll();
        Assertions.assertEquals(result.size(), cryptoDtoList.size());
    }

    @Test
    void getByIdTest() {
        CryptoDto           dto1    = new CryptoDto();
        Map<String, Object> testMap = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(cryptoServiceHelper.findById(1L)).thenReturn(dto1);
        when(cryptoServiceHelper.getMapFromCrypto(dto1, true)).thenReturn(testMap);

        Map<String, Object> result = target.getById(1L);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void createEntityTest() throws InvalidInputException, InvalidNameException {
        Crypto    cryptoTest = new Crypto();
        CryptoDto dto1       = new CryptoDto();

        Map<String, Object> testMap = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(parsingService.validateParamsAndReturnCrypto(req)).thenReturn(cryptoTest);
        when(cryptoServiceHelper.save(cryptoTest)).thenReturn(dto1);
        when(cryptoServiceHelper.getMapFromCrypto(dto1, false)).thenReturn(testMap);

        Map<String, Object> result = target.createEntity(req);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void updateEntityTest() throws InvalidInputException, InvalidNameException {
        CryptoDto           dto1       = new CryptoDto();
        Crypto              cryptoTest = new Crypto();
        Map<String, Object> testMap    = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(cryptoServiceHelper.findById(1L)).thenReturn(dto1);
        when(parsingService.validateParamsAndReturnCrypto(req)).thenReturn(cryptoTest);
        when(cryptoServiceHelper.save(cryptoTest)).thenReturn(dto1);
        when(cryptoServiceHelper.getMapFromCrypto(dto1, false)).thenReturn(testMap);

        Map<String, Object> result = target.updateEntity(1L, req);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void deleteEntityTest() {
        CryptoDto dto1 = new CryptoDto();

        when(cryptoServiceHelper.findById(1L)).thenReturn(dto1);
        doNothing().when(cryptoServiceHelper).deleteById(1L);
        Map<String, Object> result = target.deleteEntity(1L);
        Assertions.assertEquals(result.get("success"), true);

    }
}