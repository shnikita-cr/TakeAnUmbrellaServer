package com.takeanumbrella.takeanumbrellaserver.client.paymentmethod;

import com.takeanumbrella.takeanumbrellaserver.passwordUtil.PasswordUtils;

import java.math.BigDecimal;

public class PayPalPayment implements PaymentMethod {
    private String email;
    private String passwordHash;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.passwordHash = PasswordUtils.hashPassword(password);
    }

    @Override
    public boolean pay(BigDecimal amount) {
        // Здесь выполняется логика оплаты через PayPal
        System.out.println("Оплата через PayPal на сумму: " + amount);
        // Запрос к API PayPal, проверка аккаунта
        // Если платеж успешен, вернуть true
        return true;
    }

    @Override
    public boolean refund(BigDecimal amount) {
        // Логика возврата средств через PayPal
        System.out.println("Возврат через PayPal суммы: " + amount);
        // Если возврат успешен, вернуть true
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }
}
