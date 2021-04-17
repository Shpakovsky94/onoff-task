package com.example.onofftask.service;

import com.example.onofftask.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService{

    @Autowired
    private final CryptoRepository repository;

    public CryptoServiceImpl(CryptoRepository repository) {
        this.repository = repository;
    }
}
