package com.example.onofftask.service;

import com.example.onofftask.dao.CryptoDao;
import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        List<Crypto> result = repository.findAll();
        for (Crypto c : result) {
            BigDecimal price        = getCryptoMarketValue(c.getName()) != null ? getCryptoMarketValue(c.getName()) : BigDecimal.ZERO;
            BigDecimal currentPrice = price.multiply(BigDecimal.valueOf(c.getAmount()));

            c.setCurrentMarketPrice(currentPrice);
        }
        return result;
    }

    @Override public Crypto findById(Long id) throws EntityNotFoundException{
        Crypto     crypto       = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        BigDecimal price        = getCryptoMarketValue(crypto.getName()) != null ? getCryptoMarketValue(crypto.getName()) : BigDecimal.ZERO;
        BigDecimal currentPrice = price.multiply(BigDecimal.valueOf(crypto.getAmount()));

        crypto.setCurrentMarketPrice(currentPrice);
        return crypto;
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

    @Override
    public BigDecimal getCryptoMarketValue(String name) {
        return parsingService.parseDataFromJson(name).getValueInEuros();
    }

    @Override
    public Map<String, Object> getMapFromCrypto(
        Crypto crypto,
        boolean getCurrentMarketPrice
    ) {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("id", crypto.getId());
        result.put("name", crypto.getName());
        result.put("amount", crypto.getAmount());
        result.put("created at", crypto.getCreationDate());
        result.put("wallet", crypto.getWallet());
        result.put("purchase market value", crypto.getPurchaseMarketValue());

        if (getCurrentMarketPrice) {
            result.put("currentMarketPrice", crypto.getCurrentMarketPrice());
        }
        return result;
    }
}
