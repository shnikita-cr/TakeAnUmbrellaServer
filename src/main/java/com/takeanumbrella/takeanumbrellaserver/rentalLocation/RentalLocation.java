package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RentalLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_sequence", allocationSize = 1)
    private Long locationId;

    private String adress;

    @Embedded
    private Coordinates location;

    @OneToMany(mappedBy = "umbrellaId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Umbrella> validUmbrellas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RentalLocationStatus status;

    public RentalLocation() {}

    public RentalLocation(String adress, Coordinates location, ArrayList<Umbrella> validUmbrellas, RentalLocationStatus status) {
        this.adress = adress;
        this.location = location;
        this.validUmbrellas = validUmbrellas;
        this.status = status;
    }

    public List<Umbrella> getValidUmbrellas() {
        return validUmbrellas;
    }

    public void addUmbrella(Umbrella newUmbrella) {
        validUmbrellas.add(newUmbrella);
        newUmbrella.setLocation(this);
        newUmbrella.setStatus(UmbrellaStatus.FREE);
    }

    public void removeUmbrella(Umbrella umbrella) {
        validUmbrellas.remove(umbrella);
        umbrella.setLocation(null);
        umbrella.setStatus(UmbrellaStatus.BUSY);
    }

    public int countAvailableUmbrellas() {
        return validUmbrellas.size();
    }

}
