package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalLocationService {

    public List<RentalLocation> getValidLocations(String cityName) {
        List<RentalLocation> places = new ArrayList<RentalLocation>();
        return places; //FIXME getPlacesValid
    }
}
