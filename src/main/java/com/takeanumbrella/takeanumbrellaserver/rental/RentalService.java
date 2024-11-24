package com.takeanumbrella.takeanumbrellaserver.rental;

import com.takeanumbrella.takeanumbrellaserver.client.Client;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PaymentMethod;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaSize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalService {

//    private final RentalRepository rentalRepository;
//
//    public RentalService(RentalRepository rentalRepository) {
//        this.rentalRepository = rentalRepository;
//    }

    public Long rentalStart(RentalStartForm rental) {
        return 11L; //FIXME
    }

    public Rental rentalOpenInfo(Long rentalId) {
//        Optional<Rental> foundRental = rentalRepository.findById(rentalId);
//        return foundRental.orElse(null);
        return null; //FIXME
    }
}
