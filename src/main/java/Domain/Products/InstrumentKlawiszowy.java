package Domain.Products;

import Domain.Common.Money;

public class InstrumentKlawiszowy extends Product {
    public InstrumentKlawiszowy() {
        setCategory(Category.InstrumentKlawiszowy);
    }

    public InstrumentKlawiszowy(String name, Money price) {
        super(name, price);
    }
}
