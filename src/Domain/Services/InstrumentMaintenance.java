package Domain.Services;

import Domain.Common.Money;

import java.util.ArrayList;

public class InstrumentMaintenance extends ShopService {
    public InstrumentMaintenance() {
    }

    public InstrumentMaintenance(String name, Money price) {
        super(name, price, new ArrayList<>());
    }
}
