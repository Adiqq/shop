package Domain;

import Infrastructure.Entity;

/**
 * Created by Adiq on 16.10.2016.
 */
public class ShopService extends Entity {
    private String name;
    private Money price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
