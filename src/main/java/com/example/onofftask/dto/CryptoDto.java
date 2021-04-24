package com.example.onofftask.dto;

import java.math.BigDecimal;
import java.util.Date;


public class CryptoDto {

    private Long id;
    private String name;
    private Double amount;
    private String wallet;
    private Date creationDate;
    private BigDecimal purchaseMarketValue;
    private BigDecimal currentMarketValue;

    public CryptoDto() {
    }

    public CryptoDto(
        Long id,
        String name,
        Double amount,
        String wallet,
        Date creationDate,
        BigDecimal purchaseMarketValue,
        BigDecimal currentMarketValue
    ) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.wallet = wallet;
        this.creationDate = creationDate;
        this.purchaseMarketValue = purchaseMarketValue;
        this.currentMarketValue = currentMarketValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPurchaseMarketValue() {
        return purchaseMarketValue;
    }

    public void setPurchaseMarketValue(BigDecimal purchaseMarketValue) {
        this.purchaseMarketValue = purchaseMarketValue;
    }

    public BigDecimal getCurrentMarketValue() {
        return currentMarketValue;
    }

    public void setCurrentMarketValue(BigDecimal currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }
}
