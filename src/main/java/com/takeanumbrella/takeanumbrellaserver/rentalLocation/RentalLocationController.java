package com.takeanumbrella.takeanumbrellaserver.rentalLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentalLocationController {
    private final RentalLocationService rentalLocationService;

    @Autowired
    public RentalLocationController(RentalLocationService rentalLocationService) {
        this.rentalLocationService = rentalLocationService;
    }

    @GetMapping(path = "api/v1/locations/{querySearch}")
    public List<RentalLocation> getPlacesValid(@PathVariable("querySearch") String querySearch) {
        return rentalLocationService.getValidLocations(querySearch);
    }
}
