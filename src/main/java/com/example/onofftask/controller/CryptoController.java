package com.example.onofftask.controller;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.extention.EntityNotFoundException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.model.CryptoMapper;
import com.example.onofftask.service.CryptoService;
import com.example.onofftask.service.ParsingService;
import java.math.BigDecimal;
import java.util.List;
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
    private final ParsingService parsingService;

    @Autowired
    public CryptoController(CryptoService cryptoService, ParsingService parsingService) {
        this.cryptoService = cryptoService;
        this.parsingService = parsingService;
    }

    @GetMapping(value="/show-entities")
    public List<Crypto> getAll(){
        return cryptoService.findAll();
    }

    @GetMapping(value="/entities/{id}")
    public ResponseEntity<Crypto> getById(@PathVariable("id") Long id) {
            Crypto crypto = cryptoService.findById(id)
                                         .orElseThrow(()->new EntityNotFoundException(id));
            crypto.setCurrentMarketValue(cryptoService.getCryptoMarketValue(crypto.getName()));
        return ResponseEntity.ok().body(crypto);
    }

    @PostMapping(value="/entities")
    public Crypto createEntity(@RequestParam("name") String name,
                                               @RequestParam("amount") String  amount,
                                               @RequestParam("wallet") String wallet) {
        Crypto crypto = new Crypto(name, new BigDecimal(amount), wallet);
        Crypto addedCrypto = cryptoService.save(crypto);
        return addedCrypto;
    }

    @PutMapping(value="/entities/{id}")
    public ResponseEntity<Crypto> updateEntity(@PathVariable("id") Long id, @RequestBody CryptoDto cryptoDto) {
        Crypto prd = cryptoService.findById(id)
                                  .orElseThrow(()->new EntityNotFoundException(id));

        Crypto newCrypto = CryptoMapper.dtoToEntity(cryptoDto);
        newCrypto.setId(prd.getId());
        cryptoService.save(newCrypto);
        return ResponseEntity.ok().body(newCrypto);
    }

    @DeleteMapping(value="/entities/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") Long id) {
        Crypto crypto = cryptoService.findById(id)
                                  .orElseThrow(()->new EntityNotFoundException(id));
        cryptoService.deleteById(crypto.getId());
        return ResponseEntity.ok().body("Entity  with ID : "+id+" deleted with success!");
    }
}