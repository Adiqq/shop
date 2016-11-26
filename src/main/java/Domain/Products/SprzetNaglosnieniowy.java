package Domain.Products;

import Domain.Common.Money;

public class SprzetNaglosnieniowy extends Product {
    public SprzetNaglosnieniowy() {
        setCategory(Category.SprzetNaglosnieniowy);
    }

    public SprzetNaglosnieniowy(String name, Money price) {
        super(name, price);
    }
}
