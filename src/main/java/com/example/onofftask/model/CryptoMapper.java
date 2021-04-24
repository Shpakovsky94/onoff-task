package com.example.onofftask.model;

import com.example.onofftask.dto.CryptoDto;

public class CryptoMapper {

        public static Crypto dtoToEntity(CryptoDto cryptoDto) {
            Crypto crypto = new Crypto();

            crypto.setName(cryptoDto.getName());
            crypto.setWallet(cryptoDto.getWallet());
            crypto.setAmount(cryptoDto.getAmount());
            crypto.setCurrentMarketPrice(cryptoDto.getCurrentMarketValue());
            crypto.setPurchaseMarketValue(cryptoDto.getPurchaseMarketValue());
            return crypto;
        }
        public static CryptoDto entityToDto(Crypto crypto) {
            CryptoDto cryptoDto = new CryptoDto();

            cryptoDto.setName(crypto.getName());
            cryptoDto.setWallet(crypto.getWallet());
            cryptoDto.setAmount(crypto.getAmount());
            cryptoDto.setCurrentMarketValue(crypto.getCurrentMarketPrice());
            cryptoDto.setPurchaseMarketValue(crypto.getPurchaseMarketValue());
            return cryptoDto;
        }
    }

