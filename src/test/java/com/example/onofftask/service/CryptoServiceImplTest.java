package com.example.onofftask.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.onofftask.dao.CryptoDao;
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
class CryptoServiceImplTest {

    CryptoServiceImpl cryptoService;

    @Mock
    HttpServletRequest req;
    @Mock
    CryptoMapper       mapper;
    @Mock
    CryptoDao          cryptoDao;
    @Mock
    ExceptionResolver  exceptionResolver;
    @Mock
    BitfinexService    bitfinexService;


    @BeforeEach
    public void setUp() {
        req = mock(HttpServletRequest.class);
        mapper = mock(CryptoMapper.class);
        cryptoDao = mock(CryptoDao.class);
        exceptionResolver = mock(ExceptionResolver.class);
        bitfinexService = mock(BitfinexService.class);
        cryptoService = new CryptoServiceImpl(cryptoDao, bitfinexService, mapper);
    }

    @Test
    void findAll() {
        Crypto crypto1 = new Crypto("BTCEUR", 10.2, "1");
        Crypto crypto2 = new Crypto("BTCEUR", 10.2, "1");

        List<Crypto> cryptoList = new ArrayList<>();
        cryptoList.add(crypto1);
        cryptoList.add(crypto2);

        when(cryptoDao.findAll()).thenReturn(cryptoList);
        when(mapper.convertToDto(crypto1)).thenReturn(new CryptoDto());
        when(mapper.convertToDto(crypto2)).thenReturn(new CryptoDto());
        when(bitfinexService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN, BigDecimal.TEN);

        List<CryptoDto> result = cryptoService.findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void findById() {
        Crypto    crypto1 = new Crypto("BTCEUR", 10.2, "1");
        CryptoDto dto1    = new CryptoDto("BTCEUR", 10.2, "1");

        when(cryptoDao.findById(1L)).thenReturn(java.util.Optional.of(crypto1));
        when(mapper.convertToDto(crypto1)).thenReturn(dto1);
        when(bitfinexService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN);
        CryptoDto result = cryptoService.findById(1L);

        Assertions.assertNotNull(result);
    }

    @Test
    void save() {
        Crypto    crypto1 = new Crypto("BTCEUR", 10.2, "1");
        CryptoDto dto1    = new CryptoDto("BTCEUR", 10.2, "1");

        when(bitfinexService.getCurrentMarketPriceFromApi("BTCEUR")).thenReturn(BigDecimal.TEN);
        when(cryptoDao.save(crypto1)).thenReturn(crypto1);
        when(mapper.convertToDto(crypto1)).thenReturn(dto1);

        CryptoDto result = cryptoService.save(crypto1);
        Assertions.assertNotNull(result);
    }

    @Test
    void deleteById() {
        doNothing().when(cryptoDao).deleteById(1L);

        cryptoService.deleteById(1L);
    }

    @Test
    void getMapFromCrypto_getCurrentMarketPrice_is_true() {
        CryptoDto dto1 = new CryptoDto("BTCEUR", 10.2, "1");
        dto1.setCurrentMarketPrice(BigDecimal.TEN);

        Map<String, Object> result = cryptoService.getMapFromCrypto(dto1, true);
        Assertions.assertEquals(BigDecimal.TEN, result.get("currentMarketPrice"));
    }

    @Test
    void getMapFromCrypto_getCurrentMarketPrice_is_false() {
        CryptoDto dto1 = new CryptoDto("BTCEUR", 10.2, "1");
        dto1.setCurrentMarketPrice(BigDecimal.TEN);

        Map<String, Object> result = cryptoService.getMapFromCrypto(dto1, false);
        Assertions.assertNull(result.get("currentMarketPrice"));
    }
}