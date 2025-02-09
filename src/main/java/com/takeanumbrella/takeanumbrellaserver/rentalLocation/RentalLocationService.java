package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalLocationService {

    private final RentalLocationRepository repository;

    public RentalLocationService(RentalLocationRepository repository) {
        this.repository = repository;
    }

    public List<RentalLocation> getValidLocations(String cityName) {
        List<RentalLocation> places = repository.findAll();
        return places; //FIXME getPlacesValid on query
    }
}