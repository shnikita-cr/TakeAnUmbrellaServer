package com.takeanumbrella.takeanumbrellaserver.client;

import com.takeanumbrella.takeanumbrellaserver.client.notification.Notification;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PaymentMethod;
import com.takeanumbrella.takeanumbrellaserver.passwordUtil.PasswordUtils;
import com.takeanumbrella.takeanumbrellaserver.rental.Rental;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name="client") // название сущности
@Table(name="users") //имя таблицы в бд
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "users_userid_seq", allocationSize = 1)
    //sequenceName = имя столбца в БД, который будет использоваться для генерации id
    @Column(name="user_id", unique = true, nullable = false) //подпись как в бд
    private Long clientId;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(name="user_email", nullable = false)
    private String email;
    @Column(name="user_password_hash")
    private String passwordHash;
//    private Map<String, PaymentMethod> paymentMethods = new HashMap<>();
//    private List<Rental> rentalHistory = new ArrayList<>();
//    private List<Rental> currentRentals = new ArrayList<>();
//    private List<Notification> notifications = new ArrayList<>();

    public Client(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Client() { //TODO пустой конструктор должен быть у ENTITY иначе ошибка

    }

//    public void addRental(Umbrella umbrella, Tariff tariff, String namePaymentMethod) {
//        PaymentMethod paymentNow = paymentMethods.get(namePaymentMethod);
//        Rental rental = new Rental( this, umbrella, tariff, paymentNow);
//        currentRentals.add(rental);
//        umbrella.setStatus(UmbrellaStatus.BUSY);
//        umbrella.setLocation(null);
//    }

//    public void completeRental(Rental rental, RentalLocation endLocation) {
//        if (rental.endRental(endLocation)) {
//            currentRentals.remove(rental);
//            rentalHistory.add(rental);
//        }
//    }

//    public void addNotification(String message) {
//        Notification notification = new Notification(message);
//        notifications.add(notification);
//    }

//    public void addPaymentMethod(String namePayment, PaymentMethod paymentMethod) {
//        paymentMethods.put(namePayment, paymentMethod);
//    }

    public void updatePassword(String prevPassword, String newPassword) {
        if (PasswordUtils.hashPassword(prevPassword).equals(passwordHash)) {
            passwordHash = PasswordUtils.hashPassword(newPassword);
        }
    }

//    public Map<String, PaymentMethod> getPaymentMethods() {
//        return paymentMethods;
//    }
//
//    public List<Rental> getRentalHistory() {
//        return rentalHistory;
//    }
//
//    public List<Rental> getCurrentRentals() {
//        return currentRentals;
//    }
//
//    public List<Notification> getNotifications() {
//        return notifications;
//    }

//    @Override
//    public String toString() {
//        return "Client{" +
//                "clientId=" + clientId +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", passwordHash='" + passwordHash + '\'' +
//                ", paymentMethods=" + paymentMethods +
//                ", rentalHistory=" + rentalHistory +
//                ", currentRentals=" + currentRentals +
//                ", notifications=" + notifications +
//                '}';
//    }


    public String getName() {//TODO для полей, которые должны попасть в ответ GETи тд ДОЛЖНЫ БЫТЬ ГЕТТЕРЫ
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Long getClientId() {
        return clientId;
    }
}