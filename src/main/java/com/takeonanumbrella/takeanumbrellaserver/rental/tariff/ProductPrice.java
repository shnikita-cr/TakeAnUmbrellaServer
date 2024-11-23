package com.takeonanumbrella.takeanumbrellaserver.rental.tariff;
import java.util.Currency;
import java.math.BigDecimal;

public class ProductPrice {
    Currency currency;
    BigDecimal amount;

    public ProductPrice(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
