package com.takeanumbrella.takeanumbrellaserver.rental;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Long rentalStart(RentalStartForm rental) {
        return 11L; //FIXME
    }

    public Rental rentalOpenInfo(Long rentalId) {
//        Optional<Rental> foundRental = rentalRepository.findById(rentalId);
//        return foundRental.orElse(null);
        return null; //FIXME
    }

    public List<Rental> rentalInfo(Long clientId) {
        return rentalRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client_id"), clientId));
    }
}
