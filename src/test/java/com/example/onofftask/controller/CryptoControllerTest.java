package com.example.onofftask.controller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.exception.InvalidInputException;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.ExceptionResolver;
import com.example.onofftask.service.BitfinexService;
import com.example.onofftask.service.CryptoService;
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

    CryptoController controller;

    @Mock
    HttpServletRequest req;
    @Mock
    CryptoService      cryptoService;
    @Mock
    ExceptionResolver  exceptionResolver;
    @Mock
    BitfinexService    bitfinexService;

    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        cryptoService = mock(CryptoService.class);
        exceptionResolver = mock(ExceptionResolver.class);
        bitfinexService = mock(BitfinexService.class);
        controller = new CryptoController(cryptoService, exceptionResolver, bitfinexService);
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
        when(cryptoService.findAll()).thenReturn(cryptoDtoList);

        List<CryptoDto> result = controller.getAll();
        Assertions.assertEquals(result.size(), cryptoDtoList.size());
    }

    @Test
    void getByIdTest() {
        CryptoDto           dto1    = new CryptoDto();
        Map<String, Object> testMap = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(cryptoService.findById(1L)).thenReturn(dto1);
        when(cryptoService.getMapFromCrypto(dto1, true)).thenReturn(testMap);

        Map<String, Object> result = controller.getById(1L);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void createEntityTest() throws InvalidInputException, InvalidNameException {
        Crypto    cryptoTest = new Crypto();
        CryptoDto dto1       = new CryptoDto();

        Map<String, Object> testMap = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(bitfinexService.validateParamsAndReturnCrypto(req)).thenReturn(cryptoTest);
        when(cryptoService.save(cryptoTest)).thenReturn(dto1);
        when(cryptoService.getMapFromCrypto(dto1, false)).thenReturn(testMap);

        Map<String, Object> result = controller.createEntity(req);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void updateEntityTest() throws InvalidInputException, InvalidNameException {
        CryptoDto           dto1       = new CryptoDto();
        Crypto              cryptoTest = new Crypto();
        Map<String, Object> testMap    = new LinkedHashMap<>();
        testMap.put("mock-key", "mock-value");

        when(cryptoService.findById(1L)).thenReturn(dto1);
        when(bitfinexService.validateParamsAndReturnCrypto(req)).thenReturn(cryptoTest);
        when(cryptoService.save(cryptoTest)).thenReturn(dto1);
        when(cryptoService.getMapFromCrypto(dto1, false)).thenReturn(testMap);

        Map<String, Object> result = controller.updateEntity(1L, req);
        Assertions.assertEquals(result.size(), testMap.size());
    }

    @Test
    void deleteEntityTest() {
        CryptoDto dto1 = new CryptoDto();

        when(cryptoService.findById(1L)).thenReturn(dto1);
        doNothing().when(cryptoService).deleteById(1L);
        Map<String, Object> result = controller.deleteEntity(1L);
        Assertions.assertEquals(true, result.get("success"));

    }
}