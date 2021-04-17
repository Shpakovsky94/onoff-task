package com.example.onofftask.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CryptoDto {

    private  Long id;
    private String name;
    private BigDecimal amount;
    private String wallet;
    private Date creationDate;
    private BigDecimal purchaseMarketValue;
    private BigDecimal currentMarketValue;
}
