package com.example.onofftask.service;

import com.example.onofftask.dao.CryptoRepository;
import com.example.onofftask.model.Crypto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService{

    private final CryptoRepository repository;

    @Autowired
    public CryptoServiceImpl(CryptoRepository repository) {
        this.repository = repository;
    }

    @Override public List<Crypto> findAll() {
        System.out.print("sww");
        return repository.findAll();
    }

    @Override public Optional<Crypto> findById(Long id) {
        return repository.findById(id);
    }

    @Override public void save(Crypto crypto) {
        repository.save(crypto);
    }

    @Override public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
