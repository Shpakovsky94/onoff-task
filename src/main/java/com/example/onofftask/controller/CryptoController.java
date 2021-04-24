package com.example.onofftask.controller;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.model.CryptoMapper;
import com.example.onofftask.service.CryptoService;
import com.example.onofftask.validator.DataValidator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService  cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping(value="/show-entities")
    public List<Crypto> getAll(){
        return cryptoService.findAll();
    }

    @GetMapping(value = "/entities/{id}")
    public ResponseEntity<Crypto> getById(@PathVariable("id") Long id) {
        Crypto crypto = cryptoService.findById(id);
        crypto.setCurrentMarketPrice(cryptoService.getCryptoMarketValue(crypto.getName()));
        return ResponseEntity.ok().body(crypto);
    }

    @PostMapping(value = "/entities")
    public Map<String, Object> createEntity(
        @RequestParam("name") String name,
        @RequestParam("amount") String amount,
        @RequestParam("wallet") String wallet
    ) {
        Map<String, Object> result;

        try {
            DataValidator.validateString(name);
            DataValidator.validateString(amount);
            DataValidator.validateString(wallet);

            Crypto crypto = new Crypto(name, new BigDecimal(amount), wallet);
            Crypto addedCrypto = cryptoService.save(crypto);

            result = cryptoService.getMapFromCrypto(addedCrypto, false);


        } catch (Exception e) {
            result = cryptoService.handleException(e);
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping(value = "/entities/{id}")
    public ResponseEntity<Crypto> updateEntity(
        @PathVariable("id") Long id,
        @RequestBody CryptoDto cryptoDto
    ) {
        Crypto prd = cryptoService.findById(id);

        Crypto newCrypto = CryptoMapper.dtoToEntity(cryptoDto);
        newCrypto.setId(prd.getId());
        cryptoService.save(newCrypto);
        return ResponseEntity.ok().body(newCrypto);
    }

    @DeleteMapping(value="/entities/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") Long id) {
        Crypto crypto = cryptoService.findById(id);
        cryptoService.deleteById(crypto.getId());
        return ResponseEntity.ok().body("Entity  with ID : "+id+" deleted with success!");
    }
}