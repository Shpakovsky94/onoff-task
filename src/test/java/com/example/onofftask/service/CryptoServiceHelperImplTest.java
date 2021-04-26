package com.example.onofftask.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.mapper.CryptoMapper;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.ExceptionResolver;
import java.math.BigDecimal;
import java.util.ArrayList;
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
class CryptoServiceHelperImplTest {

    CryptoServiceHelperImpl target;

    @Mock
    HttpServletRequest req;
    @Mock
    CryptoMapper       mapper;
    @Mock
    CryptoService      cryptoService;
    @Mock
    ExceptionResolver  exceptionResolver;
    @Mock
    ParsingService     parsingService;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        mapper = mock(CryptoMapper.class);
        cryptoService = mock(CryptoService.class);
        exceptionResolver = mock(ExceptionResolver.class);
        parsingService = mock(ParsingService.class);
        target = new CryptoServiceHelperImpl(cryptoService, parsingService, mapper);
    }

    @Test
    void findAll() {
        Crypto crypto1 = new Crypto("BTCEUR", 10.2, "1");
        Crypto crypto2 = new Crypto("BTCEUR", 10.2, "1");

        List<Crypto> cryptoList = new ArrayList<>();
        cryptoList.add(crypto1);
        cryptoList.add(crypto2);

        when(cryptoService.findAll()).thenReturn(cryptoList);
        when(mapper.convertToDto(crypto1)).thenReturn(new CryptoDto());
        when(mapper.convertToDto(crypto2)).thenReturn(new CryptoDto());
        when(parsingService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN, BigDecimal.TEN);

        List<CryptoDto> result = target.findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void findById() {
        Crypto    crypto1 = new Crypto("BTCEUR", 10.2, "1");
        CryptoDto dto1    = new CryptoDto("BTCEUR", 10.2, "1");

        when(cryptoService.findById(1L)).thenReturn(crypto1);
        when(mapper.convertToDto(crypto1)).thenReturn(dto1);
        when(parsingService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN);
        CryptoDto result = target.findById(1L);

        Assertions.assertNotNull(result);
    }

    @Test
    void save() {
        Crypto    crypto1 = new Crypto("BTCEUR", 10.2, "1");
        CryptoDto dto1    = new CryptoDto("BTCEUR", 10.2, "1");

        when(parsingService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN);
        when(cryptoService.save(crypto1)).thenReturn(crypto1);
        when(mapper.convertToDto(crypto1)).thenReturn(dto1);

        CryptoDto result = target.save(crypto1);
        Assertions.assertNotNull(result);
    }

    @Test
    void deleteById() {
        doNothing().when(cryptoService).deleteById(1L);

        target.deleteById(1L);
    }

    @Test
    void getMapFromCrypto_getCurrentMarketPrice_is_true() {
        CryptoDto dto1 = new CryptoDto("BTCEUR", 10.2, "1");
        dto1.setCurrentMarketPrice(BigDecimal.TEN);

        Map<String, Object> result = target.getMapFromCrypto(dto1, true);
        Assertions.assertEquals(BigDecimal.TEN, result.get("currentMarketPrice"));
    }

    @Test
    void getMapFromCrypto_getCurrentMarketPrice_is_false() {
        CryptoDto dto1 = new CryptoDto("BTCEUR", 10.2, "1");
        dto1.setCurrentMarketPrice(BigDecimal.TEN);

        Map<String, Object> result = target.getMapFromCrypto(dto1, false);
        Assertions.assertNull(result.get("currentMarketPrice"));
    }
}