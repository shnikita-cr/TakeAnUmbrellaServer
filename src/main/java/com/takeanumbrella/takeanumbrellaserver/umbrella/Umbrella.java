package com.takeanumbrella.takeanumbrellaserver.umbrella;

import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaColor;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaSize;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

public class Umbrella {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "umbrella_seq")
    @SequenceGenerator(name = "umbrella_seq", sequenceName = "umbrella_sequence", allocationSize = 1)
    private Long umbrellaId;
    private final UmbrellaSize size;
    private final UmbrellaColor color;
    private UmbrellaStatus status;
    private RentalLocation location;

    public Umbrella(UmbrellaSize size, UmbrellaColor color, UmbrellaStatus status, RentalLocation location) {
        this.size = size;
        this.color = color;
        this.status = status;
        this.location = location;
    }

    public boolean isFree(){
        return status== UmbrellaStatus.FREE;
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
