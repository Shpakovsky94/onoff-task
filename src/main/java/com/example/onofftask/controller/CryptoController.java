package com.example.onofftask.controller;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.exception.InvalidNameException;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.model.CryptoMapper;
import com.example.onofftask.service.CryptoService;
import com.example.onofftask.resolver.ExceptionResolver;
import com.example.onofftask.resolver.HttpRequestResolver;
import com.example.onofftask.service.ParsingService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService       cryptoService;
    private final HttpRequestResolver httpRequestResolver;
    private final ExceptionResolver exceptionResolver;
    private final ParsingService    parsingService;

    @Autowired
    public CryptoController(
        CryptoService cryptoService,
        HttpRequestResolver httpRequestResolver,
        ExceptionResolver exceptionResolver,
        ParsingService parsingService
    ) {
        this.cryptoService = cryptoService;
        this.httpRequestResolver = httpRequestResolver;
        this.exceptionResolver = exceptionResolver;
        this.parsingService = parsingService;
    }

    @GetMapping(value="/show-entities")
    public List<Crypto> getAll(){
        return cryptoService.findAll();
    }

    @GetMapping(value = "/entities/{id}")
    public Map<String, Object> getById(@PathVariable("id") Long id) {
        try {
            Crypto crypto = cryptoService.findById(id);
            return cryptoService.getMapFromCrypto(crypto, false);
        }catch (Exception e){
            return exceptionResolver.handleException(e);
        }
    }

    @PostMapping(value = "/entities")
    public Map<String, Object> createEntity(final HttpServletRequest request) {

        try {
            String name = httpRequestResolver.getParam("name", request);
            Double amount = httpRequestResolver.getDoubleParam("amount", request);
            String wallet = httpRequestResolver.getParam("wallet", request);
            List<String> symbolsList = parsingService.parseDataFromJsonToArray(name, true);
            if (!symbolsList.contains(name.toLowerCase())){
                throw new InvalidNameException();
            }

                Crypto addedCrypto = cryptoService.save(new Crypto(name, amount, wallet));

            return cryptoService.getMapFromCrypto(addedCrypto, false);

        } catch (Exception e) {
            return exceptionResolver.handleException(e);
        }
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