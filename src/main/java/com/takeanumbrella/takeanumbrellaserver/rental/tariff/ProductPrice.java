package com.takeanumbrella.takeanumbrellaserver.rental.tariff;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Embeddable
public class ProductPrice {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "currency", nullable = false)
    private String currency;

    protected ProductPrice() {}

    public ProductPrice(BigDecimal price, String currency) {
        this.price = price;
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
