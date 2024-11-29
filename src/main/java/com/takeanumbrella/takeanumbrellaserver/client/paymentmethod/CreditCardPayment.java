package com.takeanumbrella.takeanumbrellaserver.client.paymentmethod;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "credit_card_payment")
public class CreditCardPayment extends PaymentMethod {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "card_number", nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "card_holder", nullable = false)
    private String cardHolder;

    @Column(name = "expiration_date", nullable = false, length = 5)
    private String expirationDate; // Формат MM/YY

    @Column(name = "cvv", nullable = false, length = 3)
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
