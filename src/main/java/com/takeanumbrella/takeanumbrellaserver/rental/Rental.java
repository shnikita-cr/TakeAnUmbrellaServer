package com.takeanumbrella.takeanumbrellaserver.rental;

import com.takeanumbrella.takeanumbrellaserver.client.Client;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PaymentMethod;
import com.takeanumbrella.takeanumbrellaserver.rental.states.PaymentStatus;
import com.takeanumbrella.takeanumbrellaserver.rental.states.RentalStatus;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.client.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    @SequenceGenerator(name = "rental_seq", sequenceName = "rental_sequence", allocationSize = 1)
    private Long rentalId;
    private final Client client;
    private final Umbrella umbrella;
    private final RentalLocation locationStart;
    private RentalLocation locationEnd;
    private final Timestamp startTimeStamp;
    private Timestamp endTimeStamp = null;
    private RentalStatus status;
    private final Tariff tariff;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private PaymentMethod paymentMethod;

    public Long getRentalId() {
        return rentalId;
    }

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

        BigDecimal finalCost = minutesDifference.multiply(tariff.getCost().getAmount());
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
        return startTimeStamp;
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