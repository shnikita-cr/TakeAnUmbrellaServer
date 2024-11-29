package com.takeanumbrella.takeanumbrellaserver.umbrella;

import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaColor;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaSize;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;
import jakarta.persistence.*;

@Entity
public class Umbrella {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "umbrella_seq")
    @SequenceGenerator(name = "umbrella_seq", sequenceName = "umbrella_sequence", allocationSize = 1)
    private Long umbrellaId;

    @Enumerated(EnumType.STRING)
    private UmbrellaSize size; //not final because of empty c-tor for JPA

    @Enumerated(EnumType.STRING)
    private UmbrellaColor color;

    @Enumerated(EnumType.STRING)
    private UmbrellaStatus status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private RentalLocation location;

    public Umbrella(){}

    public Umbrella(UmbrellaSize size, UmbrellaColor color, UmbrellaStatus status, RentalLocation location) {
        this.size = size;
        this.color = color;
        this.status = status;
        this.location = location;
    }

    public boolean isFree() {
        return status == UmbrellaStatus.FREE;
    }

    public Long getUmbrellaId() {
        return umbrellaId;
    }

    public UmbrellaSize getSize() {
        return size;
    }

    public UmbrellaColor getColor() {
        return color;
    }

    public UmbrellaStatus getStatus() {
        return status;
    }

    public RentalLocation getLocation() {
        return location;
    }

    public void setStatus(UmbrellaStatus status) {
        this.status = status;
    }

    public void setLocation(RentalLocation location) {
        this.location = location;
    }
}
