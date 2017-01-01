package Domain.Services;

import Domain.Common.Money;

import java.util.ArrayList;

public class InstrumentHandling extends ShopService {
    public InstrumentHandling() {
    }

    public InstrumentHandling(String name, Money pricePerHour) {
        super(name, pricePerHour, new ArrayList<>());
    }
}
