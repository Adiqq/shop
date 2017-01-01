package Domain.Services;

import Domain.Common.Money;

import java.util.ArrayList;

public class InstrumentPlayLessons extends ShopService {
    public InstrumentPlayLessons() {
    }

    public InstrumentPlayLessons(String name, Money pricePerHour) {
        super(name, pricePerHour, new ArrayList<>());
    }
}
