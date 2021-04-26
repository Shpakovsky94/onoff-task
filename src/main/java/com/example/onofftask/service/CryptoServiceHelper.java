package com.example.onofftask.service;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import java.util.List;
import java.util.Map;

public interface CryptoServiceHelper {

    List<CryptoDto> findAll();

    CryptoDto findById(Long id) throws EntityNotFoundException;

    CryptoDto save(Crypto crypto);

    void deleteById(Long id) throws EntityNotFoundException;

    Map<String, Object> getMapFromCrypto(
        CryptoDto crypto,
        boolean getCurrentMarketPrice
    );
}
