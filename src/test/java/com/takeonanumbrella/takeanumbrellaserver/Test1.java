package com.takeonanumbrella.takeanumbrellaserver;

import com.takeonanumbrella.takeanumbrellaserver.client.*;
import com.takeonanumbrella.takeanumbrellaserver.client.paymentmethod.PayPalPayment;
import com.takeonanumbrella.takeanumbrellaserver.rental.tariff.ProductPrice;
import com.takeonanumbrella.takeanumbrellaserver.rental.Rental;
import com.takeonanumbrella.takeanumbrellaserver.rental.states.RentalStatus;
import com.takeonanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeonanumbrella.takeanumbrellaserver.rentalLocation.Coordinates;
import com.takeonanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeonanumbrella.takeanumbrellaserver.rentalLocation.RentalLocationStatus;
import com.takeonanumbrella.takeanumbrellaserver.umbrella.states.ColorOfUmbrella;
import com.takeonanumbrella.takeanumbrellaserver.umbrella.states.SizeOfUmbrella;
import com.takeonanumbrella.takeanumbrellaserver.umbrella.states.StatusOfUmbrella;
import com.takeonanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    private RentalLocation loc1 = new RentalLocation("adress1", new Coordinates(1.0, 2.0), new ArrayList<>(), RentalLocationStatus.OPEN);
    private Umbrella umbrella1 = new Umbrella(SizeOfUmbrella.BIG, ColorOfUmbrella.BLACK, StatusOfUmbrella.FREE, null);

    @Test
    void testAddUmbrella() {
        loc1.addUmbrella(umbrella1);
        List<Umbrella> result = new ArrayList<>();
        result.add(umbrella1);
        assertEquals(loc1.getValidUmbrellas(), result);
        assertEquals(umbrella1.getLocation(), loc1);
        assertEquals(umbrella1.isFree(), true);
    }

    Client fedor = new Client("Fedor", "asdf@email", "PaSsWoRd");
    Tariff tariff = new Tariff("tariff1", new ProductPrice(Currency.getInstance("USD"), BigDecimal.valueOf(1)));
    PayPalPayment payPal = new PayPalPayment("email", "password");

    @Test
    void testTakeUmbrella() {
        fedor.addPaymentMethod("payPal1", payPal);
        fedor.addRental(umbrella1, tariff, "payPal1");
        assertEquals(umbrella1.isFree(), false);
        assertEquals(umbrella1.getLocation(), null);
        assertEquals(loc1.getValidUmbrellas().isEmpty(), true);
        assertEquals(fedor.getCurrentRentals().size(), 1);
    }

    @Test
    void testReturnUmbrella() {
        fedor.addPaymentMethod("payPal1", payPal);
        fedor.addRental(umbrella1, tariff, "payPal1");
        Rental currentRental = fedor.getCurrentRentals().get(0);
        fedor.completeRental(currentRental, loc1);
        assertEquals(currentRental.getLocationEnd(), loc1);
        assertEquals(currentRental.getStatus(), RentalStatus.NOT_ACTIVE);
        //assertEquals(currentRental.getRentalId(), 0L);
        assertEquals(fedor.getCurrentRentals().isEmpty(), true);
        assertEquals(fedor.getRentalHistory().get(0), currentRental);
        assertEquals(fedor.getRentalHistory().size(), 1);
    }

}