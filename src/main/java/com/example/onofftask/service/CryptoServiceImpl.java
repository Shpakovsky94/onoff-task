package com.example.onofftask.service;

import com.example.onofftask.dao.CryptoDao;
import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService{

    private final CryptoDao repository;
    private final ParsingServiceImpl parsingService;

    @Autowired
    public CryptoServiceImpl(
        CryptoDao repository,
        ParsingServiceImpl parsingService
    ) {
        this.repository = repository;
        this.parsingService = parsingService;
    }

    @Override public List<Crypto> findAll() {
        return repository.findAll();
    }

    @Override public Optional<Crypto> findById(Long id) {
        return repository.findById(id);
    }

    @Override public Crypto save(Crypto crypto) {
        crypto.setPurchaseMarketValue(getCryptoMarketValue(crypto.getName()));
        crypto.setCreationDate(new Date());
//TODO: date returned in UTC
        return repository.save(crypto);
    }

    @Override public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BigDecimal getCryptoMarketValue(String name){
        return parsingService.parseDataFromJson(name).getValueInEuros();
    }
}
