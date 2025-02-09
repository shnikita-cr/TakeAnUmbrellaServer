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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "client") // название сущности
@Table(name = "users") //имя таблицы в бд
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "users_userid_seq", allocationSize = 1)
    //sequenceName = имя столбца в БД, который будет использоваться для генерации id
    @Column(name = "user_id", unique = true, nullable = false) //подпись как в бд
    private Long clientId;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "Name cannot be empty") // Проверка на пустую строку
    @Size(max = 100, message = "Name cannot exceed 100 characters") // Ограничение длины
    private String name;

    @Column(name = "user_email", nullable = false)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid") // Проверка на корректность email
    private String email;

    @Column(name = "user_password_hash")
    @NotBlank(message = "Password hash cannot be empty")
    private String passwordHash;

    //    @ElementCollection // поле является коллекцией встроенных типов или объектов (не сущностей)
//    @CollectionTable(name = "client_payment_methods", joinColumns = @JoinColumn(name = "client_id"))
//    // name: имя таблицы для хранения коллекции.
//    // joinColumns: указывает, как коллекция связана с основной таблицей.
//    @MapKeyColumn(name = "payment_name") // столбец, который используется как ключ в Map
//    @Column(name = "payment_method")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "client_payment_methods",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id")
    )
    @MapKeyColumn(name = "payment_name")
    private final Map<String, PaymentMethod> paymentMethods = new HashMap<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //mappedBy: указывает имя поля в другой сущности, которое владеет связью.
    //cascade: настраивает каскадные операции (CascadeType.ALL включает сохранение, удаление и обновление связанных сущностей).
    //fetch: определяет стратегию загрузки (LAZY или EAGER).
    //orphanRemoval: если true, удаляет связанные объекты, которые больше не входят в коллекцию, из бд.
    private final List<Rental> rentalHistory = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Rental> currentRentals = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Notification> notifications = new ArrayList<>();

    public Client(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    //пустой конструктор для JPA
    public Client() {
    }

    public void addRental(Umbrella umbrella, Tariff tariff, String namePaymentMethod) {
        PaymentMethod paymentNow = paymentMethods.get(namePaymentMethod);
        Rental rental = new Rental(this, umbrella, tariff, paymentNow);
        currentRentals.add(rental);
        umbrella.setStatus(UmbrellaStatus.BUSY);
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

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", paymentMethods=" + paymentMethods +
                ", rentalHistory=" + rentalHistory +
                ", currentRentals=" + currentRentals +
                ", notifications=" + notifications +
                '}';
    }


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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