package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import jakarta.persistence.*;

@Embeddable
public class Coordinates {
    @Column(name = "latitude", nullable = false)
    private double latitude;  // Широта

    @Column(name = "longitude", nullable = false)
    private double longitude; // Долгота

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Latitude: " + latitude + ", Longitude: " + longitude;
    }
}

