package com.takeonanumbrella.takeanumbrellaserver.client.paymentmethod;

import java.math.BigDecimal;

public interface PaymentMethod {
    boolean pay(BigDecimal amount);

    boolean refund(BigDecimal amount);

    String getPaymentMethodName();

}
