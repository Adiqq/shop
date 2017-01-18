package Domain.Services;

import Domain.Common.Money;

import java.util.ArrayList;

/**
 * Instrument handling with price per hour
 */
public class InstrumentHandling extends ShopService {
    public InstrumentHandling() {
    }

    public InstrumentHandling(String name, Money pricePerHour) {
        super(name, pricePerHour, new ArrayList<>());
    }
}
