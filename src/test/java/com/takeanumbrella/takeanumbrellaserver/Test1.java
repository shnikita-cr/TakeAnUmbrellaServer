package com.takeanumbrella.takeanumbrellaserver;

import com.takeanumbrella.takeanumbrellaserver.client.Client;
import com.takeanumbrella.takeanumbrellaserver.client.paymentmethod.PayPalPayment;
import com.takeanumbrella.takeanumbrellaserver.rental.Rental;
import com.takeanumbrella.takeanumbrellaserver.rental.states.RentalStatus;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.ProductPrice;
import com.takeanumbrella.takeanumbrellaserver.rental.tariff.Tariff;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.Coordinates;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocation;
import com.takeanumbrella.takeanumbrellaserver.rentalLocation.RentalLocationStatus;
import com.takeanumbrella.takeanumbrellaserver.umbrella.Umbrella;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaColor;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaSize;
import com.takeanumbrella.takeanumbrellaserver.client.*;
import com.takeanumbrella.takeanumbrellaserver.umbrella.states.UmbrellaStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    private RentalLocation loc1 = new RentalLocation("adress1", new Coordinates(1.0, 2.0), new ArrayList<>(), RentalLocationStatus.OPEN);
    private Umbrella umbrella1 = new Umbrella(UmbrellaSize.BIG, UmbrellaColor.BLACK, UmbrellaStatus.FREE, null);

    @Test
    void testAddUmbrella() {
        loc1.addUmbrella(umbrella1);
        List<Umbrella> result = new ArrayList<>();
        result.add(umbrella1);
        assertEquals(loc1.getValidUmbrellas(), result);
        Assertions.assertEquals(umbrella1.getLocation(), loc1);
        assertEquals(umbrella1.isFree(), true);
    }

    Client fedor = new Client("Fedor", "asdf@email", "PaSsWoRd");
    Tariff tariff = new Tariff("tariff1", new ProductPrice(BigDecimal.valueOf(1), "USD"));
    PayPalPayment payPal = new PayPalPayment("email", "password");

    @Test
    void testTakeUmbrella() {
        fedor.addPaymentMethod("payPal1", payPal);
        fedor.addRental(umbrella1, tariff, "payPal1");
        assertEquals(umbrella1.isFree(), false);
        Assertions.assertEquals(umbrella1.getLocation(), null);
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
        Assertions.assertEquals(currentRental.getStatus(), RentalStatus.NOT_ACTIVE);
        //assertEquals(currentRental.getRentalId(), 0L);
        assertEquals(fedor.getCurrentRentals().isEmpty(), true);
        Assertions.assertEquals(fedor.getRentalHistory().get(0), currentRental);
        assertEquals(fedor.getRentalHistory().size(), 1);
    }

}