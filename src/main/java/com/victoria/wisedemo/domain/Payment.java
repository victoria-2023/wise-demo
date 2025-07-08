package com.victoria.wisedemo.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private UUID userId;
    private BigDecimal amount;
    private String currencyFrom;
    private String currencyTo;
    private Instant createdAt;

    protected Payment() { }

    public Payment(UUID userId,
                   BigDecimal amount,
                   String currencyFrom,
                   String currencyTo) {
        this.userId       = userId;
        this.amount       = amount;
        this.currencyFrom = currencyFrom;
        this.currencyTo   = currencyTo;
        this.createdAt    = Instant.now();
    }

    public Long getId()             { return id; }
    public UUID getUserId()         { return userId; }
    public BigDecimal getAmount()   { return amount; }
    public String getCurrencyFrom() { return currencyFrom; }
    public String getCurrencyTo()   { return currencyTo; }
    public Instant getCreatedAt()   { return createdAt; }
}
