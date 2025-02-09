package com.takeanumbrella.takeanumbrellaserver.client.paymentmethod;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // или SINGLE_TABLE
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean pay(BigDecimal amount) {
        return false;
    }

    public boolean refund(BigDecimal amount) {
        return false;
    }

    public String getPaymentMethodName() {
        return "need to overload";
    }

}
