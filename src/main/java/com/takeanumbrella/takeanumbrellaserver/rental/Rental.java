package com.takeanumbrella.takeanumbrellaserver.rental;

import com.takeanumbrella.takeanumbrellaserver.client.Client;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PaymentMethod;
import com.takeanumbrella.takeanumbrellaserver.rental.states.PaymentStatus;
import com.takeanumbrella.takeanumbrellaserver.rental.states.RentalStatus;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    @SequenceGenerator(name = "rental_seq", sequenceName = "rental_sequence", allocationSize = 1)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "umbrella_id", nullable = false)
    private Umbrella umbrella;

    @ManyToOne
    @JoinColumn(name = "location_start_id", nullable = false)
    private RentalLocation locationStart;

    @ManyToOne
    @JoinColumn(name = "location_end_id", nullable = false)
    private RentalLocation locationEnd;

    @Column(nullable = false)
    private Timestamp startTimeStamp;

    @Column(nullable = false)
    private Timestamp endTimeStamp = null;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @ManyToOne
    @JoinColumn(name = "tariff_id", nullable = false)
    private Tariff tariff;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    public Long getRentalId() {
        return rentalId;
    }

    public Rental(){}

    public Rental(Client client, Umbrella umbrella, Tariff tariff, PaymentMethod paymentMethod) {
        this.client = client;
        this.umbrella = umbrella;
        this.locationStart = umbrella.getLocation();
        this.startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.status = RentalStatus.ACTIVE;
        this.tariff = tariff;
        this.paymentMethod = paymentMethod;
    }

    private BigDecimal getCost(Timestamp endTime) {
        long rentalDurationMinutes = calculateDuration(endTime);
        BigDecimal minutesDifference = BigDecimal.valueOf(rentalDurationMinutes);

        BigDecimal finalCost = minutesDifference.multiply(tariff.getCost().getPrice());
        return finalCost;
    }

    private long calculateDuration(Timestamp endTime) {
        long rentalDurationMinutes = Duration.between(startTimeStamp.toInstant(), endTime.toInstant()).toMinutes();
        return rentalDurationMinutes;
    }

    public boolean endRental(RentalLocation endLocation) {
        Timestamp endTime = Timestamp.from(Instant.now()); //чтобы избежать несостыковок из-за задержек
        BigDecimal finalCost = getCost(endTime); // и размер оплаты совпадает с потраченным временем
        if (paymentMethod.pay(finalCost)) {
            endTimeStamp = endTime;
            status = RentalStatus.NOT_ACTIVE;
            paymentStatus = PaymentStatus.PAID;
            locationEnd = endLocation;
            return true;
        } else {
            // логика повтора оплаты (поменять карту/ повторить снова и тд)
        }
        return false;
    }

    public Client getClient() {
        return client;
    }

    public Umbrella getUmbrella() {
        return umbrella;
    }

    public RentalLocation getLocationStart() {
        return locationStart;
    }

    public RentalLocation getLocationEnd() {
        return locationEnd;
    }

    public Timestamp getStartTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public Timestamp getEndTimeStamp() {
        return endTimeStamp;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", client=" + (client != null ? client.toString() : "null") +
                ", umbrella=" + (umbrella != null ? umbrella.toString() : "null") +
                ", locationStart=" + (locationStart != null ? locationStart.toString() : "null") +
                ", locationEnd=" + (locationEnd != null ? locationEnd.toString() : "null") +
                ", startTimeStamp=" + startTimeStamp +
                ", endTimeStamp=" + (endTimeStamp != null ? endTimeStamp : "null") +
                ", status=" + status +
                ", tariff=" + (tariff != null ? tariff.toString() : "null") +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + (paymentMethod != null ? paymentMethod.toString() : "null") +
                '}';
    }

}