package com.example.onofftask.model;

import com.example.onofftask.dto.CryptoDto;

public class CryptoMapper {
        public static Crypto DtoToEntity(CryptoDto cryptoDto) {
            Crypto crypto = new Crypto();

            crypto.setName(cryptoDto.getName());
            crypto.setWallet(cryptoDto.getWallet());
            crypto.setAmount(cryptoDto.getAmount());
            crypto.setCreationDate(cryptoDto.getCreationDate());
            crypto.setCurrentMarketValue(cryptoDto.getCurrentMarketValue());
            crypto.setPurchaseMarketValue(cryptoDto.getPurchaseMarketValue());
            return crypto;
        }
        public static CryptoDto EntityToDto(Crypto crypto) {
            CryptoDto cryptoDto = new CryptoDto();

            cryptoDto.setName(crypto.getName());
            cryptoDto.setWallet(crypto.getWallet());
            cryptoDto.setAmount(crypto.getAmount());
            cryptoDto.setCreationDate(crypto.getCreationDate());
            cryptoDto.setCurrentMarketValue(crypto.getCurrentMarketValue());
            cryptoDto.setPurchaseMarketValue(crypto.getPurchaseMarketValue());
            return cryptoDto;
        }
    }

