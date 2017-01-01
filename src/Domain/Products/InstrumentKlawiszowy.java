package Domain.Products;

import Domain.Common.Money;

public class InstrumentKlawiszowy extends Product {
    public InstrumentKlawiszowy() {
        super(Category.InstrumentKlawiszowy);
    }

    public InstrumentKlawiszowy(String name, Money price) {
        super(Category.InstrumentKlawiszowy,name, price);
    }
}
