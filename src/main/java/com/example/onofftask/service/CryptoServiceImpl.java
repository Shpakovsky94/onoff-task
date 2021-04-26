package com.example.onofftask.service;

import com.example.onofftask.dao.CryptoDao;
import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {

    private final CryptoDao repository;

    @Autowired
    public CryptoServiceImpl(
        CryptoDao repository
    ) {
        this.repository = repository;
    }

    @Override
    public List<Crypto> findAll() {
        return repository.findAll();
    }

    @Override
    public Crypto findById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Crypto save(Crypto crypto) {
        return repository.save(crypto);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        try {
            repository.deleteById(id);
        } catch (Exception ignore) {
            throw new EntityNotFoundException(id);
        }
    }
}
