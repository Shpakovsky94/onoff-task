package com.example.onofftask.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.onofftask.controller.CryptoController;
import com.example.onofftask.dto.CryptoDto;
import com.example.onofftask.model.Crypto;
import com.example.onofftask.resolver.DateResolver;
import com.example.onofftask.resolver.ExceptionResolver;
import com.example.onofftask.service.CryptoServiceHelper;
import com.example.onofftask.service.ParsingService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
class CryptoMapperTest {

    CryptoMapper target;
@Mock
    private  ModelMapper  modelMapper;
@Mock
    private  DateResolver dateResolver;

    @BeforeEach
    public void setUp() {
        modelMapper = mock(ModelMapper.class);
        dateResolver = mock(DateResolver.class);
        target = new CryptoMapper(modelMapper, dateResolver);
    }
    @Test
    void convertToDtoTest() {
        CryptoDto dto = new CryptoDto();
        Crypto    crypto = new Crypto();
        Date date = new Date();
        crypto.setCreationDate(date);

        when(modelMapper.map(crypto, CryptoDto.class)).thenReturn(dto);
        when(dateResolver.truncateTime(date)).thenReturn("2020-10-20");

        CryptoDto result = target.convertToDto(crypto);
        Assertions.assertEquals(result.getCreationDate(), "2020-10-20");
    }
}