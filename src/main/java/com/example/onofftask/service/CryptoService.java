package com.example.onofftask.service;

import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CryptoService {

    List<Crypto> findAll();
    Optional<Crypto> findById(Long id);
    Crypto save(Crypto crypto);
    void deleteById(Long id);
    BigDecimal getCryptoMarketValue(String name);


    }
