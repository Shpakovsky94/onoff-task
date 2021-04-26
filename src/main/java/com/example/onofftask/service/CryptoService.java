package com.example.onofftask.service;

import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import java.util.List;

public interface CryptoService {

    List<Crypto> findAll();

    Crypto findById(Long id) throws EntityNotFoundException;

    Crypto save(Crypto crypto);

    void deleteById(Long id) throws EntityNotFoundException;
}
