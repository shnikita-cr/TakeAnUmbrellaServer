package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RentalLocationController {
    private final RentalLocationService rentalLocationService;

    @Autowired
    public RentalLocationController(RentalLocationService rentalLocationService) {
        this.rentalLocationService = rentalLocationService;
    }

    @GetMapping(path="api/v1/places/{cityName}")
    public List<RentalLocation> getPlacesValid(@PathVariable String cityName) {
        return rentalLocationService.getValidLocations(cityName);
    }
}
