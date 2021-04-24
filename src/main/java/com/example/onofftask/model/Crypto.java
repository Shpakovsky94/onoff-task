package com.example.onofftask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CRYPTO")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  Long id;

    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date creationDate;

    @Column(name = "WALLET")
    private String wallet;

    @Column(name = "PURCHASE_MARKET_VALUE")
    private BigDecimal purchaseMarketValue;

    @Transient
    private BigDecimal currentMarketPrice;

    public Crypto() {
    }

    public Crypto(
        String name,
        BigDecimal amount,
        String wallet
    ) {
        this.name = name;
        this.amount = amount;
        this.wallet = wallet;
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public BigDecimal getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(BigDecimal currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }
}
