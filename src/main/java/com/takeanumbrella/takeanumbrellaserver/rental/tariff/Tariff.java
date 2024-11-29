package com.takeanumbrella.takeanumbrellaserver.rental.tariff;

import jakarta.persistence.*;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Embedded
    private ProductPrice cost;

    // Конструктор для JPA
    protected Tariff() {
    }

    public Tariff(String name, ProductPrice cost) {
        this.name = name;
        this.cost = cost;
    }

    public ProductPrice getCost() {
        return cost;
    }

    public void setCost(ProductPrice cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
