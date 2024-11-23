package com.takeonanumbrella.takeanumbrellaserver.client.paymentmethod;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentMethod{
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        // Здесь выполняется логика оплаты кредитной картой
        System.out.println("Оплата кредитной картой на сумму: " + amount);
        // Проверка и обработка данных карты, запрос к платежному API
        // Если платеж успешен, вернуть true
        return true;
    }

    // Реализация метода возврата платежа
    @Override
    public boolean refund(BigDecimal amount) {
        // Логика возврата средств на кредитную карту
        System.out.println("Возврат на кредитную карту суммы: " + amount);
        // Если возврат успешен, вернуть true
        return true;
    }

    @Override
    public String getPaymentMethodName() {
        return "Кредитная карта";
    }
}
