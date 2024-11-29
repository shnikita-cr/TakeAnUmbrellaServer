package com.takeanumbrella.takeanumbrellaserver.client.paymentmethod;

import com.takeanumbrella.takeanumbrellaserver.passwordUtil.PasswordUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.math.BigDecimal;

@Entity
@Table(name = "paypal_payment")
public class PayPalPayment extends PaymentMethod {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "email", nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "password_hash", nullable = false)
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
