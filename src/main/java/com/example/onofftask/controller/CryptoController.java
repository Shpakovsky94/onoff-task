package com.example.onofftask.controller;

import com.example.onofftask.model.Crypto;
import com.example.onofftask.service.CryptoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/show-all")
    public List<Crypto> getAllEntities() {
        return cryptoService.findAll();
    }

    @GetMapping("/show-one/{id}")
    public Optional<Crypto> getEntityById(@PathVariable(value = "id") Long id) {
        return cryptoService.findById(id);
    }

    @PostMapping("/save")
    public void saveEntity(@RequestBody Crypto crypto){
        cryptoService.save(crypto);
    }

    @PostMapping("/update")
    public void updateEntity(@RequestBody Crypto crypto){
        cryptoService.save(crypto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntity(@PathVariable(value = "id") Long  id){
        cryptoService.deleteById(id);
    }
}
