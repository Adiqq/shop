package Domain.Products;

import Domain.Common.Money;

public class InstrumentPerkusyjny extends Product {
    public InstrumentPerkusyjny() {
        setCategory(Category.InstrumentPerkusyjny);
    }

    public InstrumentPerkusyjny(String name, Money price) {
        super(name, price);
    }
}
