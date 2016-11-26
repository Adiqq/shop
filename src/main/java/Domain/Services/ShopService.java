package Domain.Services;

import Domain.Common.Money;
import Infrastructure.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiq on 16.10.2016.
 */
public abstract class ShopService extends Entity {
    private String name;
    private Money price;
    private List<ShopService> services;

    public ShopService() {
    }

    public ShopService(String name, Money price, List<ShopService> services) {
        this.name = name;
        this.price = price;
        this.services = services;
    }

    public ShopService(String name, Money price) {
        this(name, price, new ArrayList<>());
    }

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

    public List<ShopService> getServices() {
        return services;
    }

    public void setServices(List<ShopService> services) {
        this.services = services;
    }
}
