package com.example.onofftask.service;

import com.example.onofftask.dao.CryptoDao;
import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.exception.EntityNotFoundException;
import com.example.onofftask.mapper.CryptoMapper;
import com.example.onofftask.model.Crypto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {


    private final CryptoDao       cryptoDao;
    private final BitfinexService bitfinexService;
    private final CryptoMapper    mapper;

    @Autowired
    public CryptoServiceImpl(
        CryptoDao cryptoDao,
        BitfinexService bitfinexService,
        CryptoMapper mapper
    ) {
        this.cryptoDao = cryptoDao;
        this.bitfinexService = bitfinexService;
        this.mapper = mapper;
    }

    @Override
    public List<CryptoDto> findAll() {
        List<Crypto>    cryptoList = cryptoDao.findAll();
        List<CryptoDto> result     = new ArrayList<>();
        for (Crypto c : cryptoList) {
            CryptoDto dto = mapper.convertToDto(c);
            dto.setCurrentMarketPrice(getCryptoMarketValueLocal(c.getName(), c.getAmount()));
            result.add(dto);
        }
        return result;
    }

    @Override
    public CryptoDto findById(Long id) throws EntityNotFoundException {
        CryptoDto dto = mapper.convertToDto(cryptoDao.findById(id)
                                                     .orElseThrow(() -> new EntityNotFoundException(id)));

        dto.setCurrentMarketPrice(getCryptoMarketValueLocal(dto.getName(), dto.getAmount()));
        return dto;
    }

    @Override
    public CryptoDto save(Crypto crypto) {
        crypto.setPurchaseMarketValue(getCryptoMarketValueLocal(crypto.getName(), null));
        crypto.setCreationDate(new Date());
        return mapper.convertToDto(cryptoDao.save(crypto));
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        cryptoDao.deleteById(id);
    }

    @Override
    public Map<String, Object> getMapFromCrypto(
        CryptoDto crypto,
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

    private BigDecimal getCryptoMarketValueLocal(
        final String name,
        final Double amount
    ) {
        BigDecimal price = bitfinexService.getCurrentMarketPriceFromApi(name);
        if (amount != null) {
            price = price.multiply(BigDecimal.valueOf(amount));
        }
        return price;

    }
}
