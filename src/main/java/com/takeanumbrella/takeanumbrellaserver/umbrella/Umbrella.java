package com.takeanumbrella.takeanumbrellaserver.umbrella;

import com.takeanumbrella.takeanumbrellaserver.umbrella.states.ColorOfUmbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.SizeOfUmbrella;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.StatusOfUmbrella;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

public class Umbrella {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "umbrella_seq")
    @SequenceGenerator(name = "umbrella_seq", sequenceName = "umbrella_sequence", allocationSize = 1)
    private Long umbrellaId;
    private final SizeOfUmbrella size;
    private final ColorOfUmbrella color;
    private StatusOfUmbrella status;
    private RentalLocation location;

    public Umbrella(SizeOfUmbrella size, ColorOfUmbrella color, StatusOfUmbrella status, RentalLocation locationId) {
        this.size = size;
        this.color = color;
        this.status = status;
        this.location = locationId;
    }

    public boolean isFree(){
        return status==StatusOfUmbrella.FREE;
    }

    public Long getUmbrellaId() {
        return umbrellaId;
    }

    public SizeOfUmbrella getSize() {
        return size;
    }

    public ColorOfUmbrella getColor() {
        return color;
    }

    public StatusOfUmbrella getStatus() {
        return status;
    }

    public RentalLocation getLocation() {
        return location;
    }

    public void setStatus(StatusOfUmbrella status) {
        this.status = status;
    }

    public void setLocation(RentalLocation location) {
        this.location = location;
    }
}
