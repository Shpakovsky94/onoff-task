package com.example.onofftask.controller;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.ExceptionResolver;
import com.example.onofftask.service.BitfinexService;
import com.example.onofftask.service.CryptoService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService     cryptoService;
    private final ExceptionResolver exceptionResolver;
    private final BitfinexService   bitfinexService;

    @Autowired
    public CryptoController(
        CryptoService cryptoService,
        ExceptionResolver exceptionResolver,
        BitfinexService bitfinexService
    ) {
        this.cryptoService = cryptoService;
        this.exceptionResolver = exceptionResolver;
        this.bitfinexService = bitfinexService;
    }

    @GetMapping(value = "/show-entities")
    public List<CryptoDto> getAll() {
        return cryptoService.findAll();
    }

    @GetMapping(value = "/entities/{id}")
    public Map<String, Object> getById(@PathVariable("id") Long id) {
        try {
            CryptoDto dto = cryptoService.findById(id);
            return cryptoService.getMapFromCrypto(dto, true);
        } catch (Exception e) {
            return exceptionResolver.handleException(e);
        }
    }

    @PostMapping(value = "/entities")
    public Map<String, Object> createEntity(final HttpServletRequest request) {

        try {
            Crypto    validatedCrypto = bitfinexService.validateParamsAndReturnCrypto(request);
            CryptoDto addedCryptoDto  = cryptoService.save(validatedCrypto);

            return cryptoService.getMapFromCrypto(addedCryptoDto, false);

        } catch (Exception e) {
            return exceptionResolver.handleException(e);
        }
    }

    @PutMapping(value = "/entities/{id}")
    public Map<String, Object> updateEntity(
        @PathVariable("id") Long id,
        final HttpServletRequest request
    ) {

        try {
            CryptoDto cryptoToUpdate = cryptoService.findById(id);
            Crypto    newCrypto      = bitfinexService.validateParamsAndReturnCrypto(request);

            newCrypto.setId(cryptoToUpdate.getId());
            CryptoDto updatedCrypto = cryptoService.save(newCrypto);
            return cryptoService.getMapFromCrypto(updatedCrypto, false);
        } catch (Exception e) {
            return exceptionResolver.handleException(e);
        }
    }

    @DeleteMapping(value = "/entities/{id}")
    public Map<String, Object> deleteEntity(@PathVariable("id") Long id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            CryptoDto crypto = cryptoService.findById(id);

            cryptoService.deleteById(crypto.getId());
            result.put("success", true);
        } catch (Exception e) {
            result = exceptionResolver.handleException(e);
        }
        return result;
    }
}