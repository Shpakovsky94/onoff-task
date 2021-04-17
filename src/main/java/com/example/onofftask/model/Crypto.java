package com.example.onofftask.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CRYPTO")
@Data
@NoArgsConstructor
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private  Long id;

    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "WALLET")
    private String wallet;

    @Column(name = "CREATED_AT")
    private Date creationDate;

    @Column(name = "PURCHASE_MARKET_VALUE")
    private BigDecimal purchaseMarketValue;

    @Column(name = "CURRENT_MARKET_VALUE")
    private BigDecimal currentMarketValue;



}
