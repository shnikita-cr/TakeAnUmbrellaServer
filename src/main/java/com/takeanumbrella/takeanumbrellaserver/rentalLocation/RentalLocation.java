package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;

import java.util.ArrayList;
import java.util.List;

public class RentalLocation {
    private Long locationId;
    private String adress;
    private Coordinates location;
    private List<Umbrella> validUmbrellas = new ArrayList<>();
    private RentalLocationStatus status;

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
