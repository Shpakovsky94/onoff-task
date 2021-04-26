package com.example.onofftask.mapper;

import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.DateResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CryptoMapper {

    private final ModelMapper modelMapper;
    private final DateResolver dateResolver;

    @Autowired
    public CryptoMapper(
        ModelMapper modelMapper,
        DateResolver dateResolver
    ) {
        this.modelMapper = modelMapper;
        this.dateResolver = dateResolver;
    }

    public CryptoDto convertToDto(Crypto crypto) {
        CryptoDto cryptoDto = modelMapper.map(crypto, CryptoDto.class);
        cryptoDto.setCreationDate(dateResolver.truncateTime(crypto.getCreationDate()));
        return cryptoDto;
    }
    }

