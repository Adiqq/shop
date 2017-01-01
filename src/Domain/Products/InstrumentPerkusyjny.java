package Domain.Products;

import Domain.Common.Money;

public class InstrumentPerkusyjny extends Product {
    public InstrumentPerkusyjny() {
        super(Category.InstrumentPerkusyjny);
    }

    public InstrumentPerkusyjny(String name, Money price) {
        super(Category.InstrumentPerkusyjny, name, price);
    }
}
