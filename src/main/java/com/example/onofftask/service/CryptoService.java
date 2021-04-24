package com.example.onofftask.service;

import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CryptoService {

    List<Crypto> findAll();

    Crypto findById(Long id);

    Crypto save(Crypto crypto);

    void deleteById(Long id);

    BigDecimal getCryptoMarketValue(String name);

    Map<String, Object> getMapFromCrypto(
        Crypto crypto,
        boolean getCurrentMarketPrice
    );

    Map<String, Object> handleException(Exception exception);

}
