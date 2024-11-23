package com.takeonanumbrella.takeanumbrellaserver.rental.tariff;

public class Tariff {
    private String name;
    private ProductPrice cost;

    public Tariff(String name, ProductPrice cost) {
        this.name = name;
        this.cost = cost;
    }

    public ProductPrice getCost() {
        return cost;
    }
}
