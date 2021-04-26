package com.example.onofftask.service;

import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CryptoService {

    List<Crypto> findAll();

    Crypto findById(Long id) throws EntityNotFoundException;

    Crypto save(Crypto crypto);

    void deleteById(Long id) throws EntityNotFoundException;
}
