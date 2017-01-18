package Domain.Services;

import Domain.Common.Money;

import java.util.ArrayList;

/**
 * Instrument playing lessons
 */
public class InstrumentPlayLessons extends ShopService {
    public InstrumentPlayLessons() {
    }

    public InstrumentPlayLessons(String name, Money pricePerHour) {

        super(name, pricePerHour, new ArrayList<>());
        setPriceType(PriceType.PerHour);
    }
}
