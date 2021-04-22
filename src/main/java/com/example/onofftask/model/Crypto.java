package com.example.onofftask.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CRYPTO")
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

    private BigDecimal currentMarketValue;

    public Crypto() {
    }

    public Crypto(
        String name,
        BigDecimal amount,
        String wallet,
        BigDecimal purchaseMarketValue
    ) {
        this.name = name;
        this.amount = amount;
        this.wallet = wallet;
        this.creationDate = new Date();
        this.purchaseMarketValue = purchaseMarketValue;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
