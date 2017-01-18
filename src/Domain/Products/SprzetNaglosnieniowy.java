package Domain.Products;

import Domain.Common.Money;

/**
 *  Sound equipment
 */
public class SprzetNaglosnieniowy extends Product {
    public SprzetNaglosnieniowy() {
        super(Category.SprzetNaglosnieniowy);
    }

    public SprzetNaglosnieniowy(String name, Money price) {
        super(Category.SprzetNaglosnieniowy, name, price);
    }
}
