package Domain.Products;

import Domain.Common.Percentage;

/**
 *  Percentage with valid values, bounded by business logic
 */
public class DiscountPercentage extends Percentage {
    public DiscountPercentage(){}
    public DiscountPercentage(int value) {
        setValue(value);
    }

    @Override
    public void setValue(int value) {
        if (value < 5 || value > 50) {
            throw new IllegalArgumentException("Discount percentage must be lower than 50% and higher than 5%");
        }
        super.setValue(value);
    }
}
