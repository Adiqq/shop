package Domain.Services;

import Domain.Common.Money;

/**
 *  Instrument leasing with price per day
 */
public class InstrumentLeasing extends ShopService {
    public InstrumentLeasing() {
    }

    public InstrumentLeasing(String name, Money pricePerDay) {
        super(name, pricePerDay);
    }
}
