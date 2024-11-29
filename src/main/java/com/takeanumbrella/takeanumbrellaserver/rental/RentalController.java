package com.takeanumbrella.takeanumbrellaserver.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(path = "api/v1/rental")
    @ResponseBody
    public Long rentalStart(@RequestBody RentalStartForm rentalStartForm) {
        return rentalService.rentalStart(rentalStartForm);
    }

    @GetMapping(path="api/v1/rentalOpenInfo/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    public Rental rentalOpenInfo(@PathVariable Long rentalId) {
        //TODO errors handling https://habr.com/ru/articles/675716/
        return rentalService.rentalOpenInfo(rentalId);
    }
}
