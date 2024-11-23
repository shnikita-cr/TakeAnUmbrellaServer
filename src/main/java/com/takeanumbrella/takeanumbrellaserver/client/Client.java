package com.takeanumbrella.takeanumbrellaserver.client;

import com.takeanumbrella.takeanumbrellaserver.client.notification.Notification;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PaymentMethod;
import com.takeanumbrella.takeanumbrellaserver.passwordUtil.PasswordUtils;
import com.takeanumbrella.takeanumbrellaserver.rental.Rental;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.rental.*;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.StatusOfUmbrella;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "umbrella_seq")
    @SequenceGenerator(name = "umbrella_seq", sequenceName = "umbrella_sequence", allocationSize = 1)
    //sequenceName = имя столбца в БД, который будет использоваться для генерации id
    private Long clientId;
    private String name;
    private String email;
    private String passwordHash;
    private Map<String, PaymentMethod> paymentMethods = new HashMap<>();
    private List<Rental> rentalHistory = new ArrayList<>();
    private List<Rental> currentRentals = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = PasswordUtils.hashPassword(password);
    }

    public void addRental(Umbrella umbrella, Tariff tariff, String namePaymentMethod) {
        PaymentMethod paymentNow = paymentMethods.get(namePaymentMethod);
        Rental rental = new Rental( this, umbrella, tariff, paymentNow);
        currentRentals.add(rental);
        umbrella.setStatus(StatusOfUmbrella.BUSY);
        umbrella.setLocation(null);
    }

    public void completeRental(Rental rental, RentalLocation endLocation) {
        if (rental.endRental(endLocation)) {
            currentRentals.remove(rental);
            rentalHistory.add(rental);
        }
    }

    public void addNotification(String message) {
        Notification notification = new Notification(message);
        notifications.add(notification);
    }

    public void addPaymentMethod(String namePayment, PaymentMethod paymentMethod) {
        paymentMethods.put(namePayment, paymentMethod);
    }

    public void updatePassword(String prevPassword, String newPassword) {
        if (PasswordUtils.hashPassword(prevPassword).equals(passwordHash)) {
            passwordHash = PasswordUtils.hashPassword(newPassword);
        }
    }

    public Map<String, PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }

    public List<Rental> getCurrentRentals() {
        return currentRentals;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}