package Domain.Services;

import Domain.Common.Money;
import Domain.Infrastructure.Entity;
import Domain.Infrastructure.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract service provided by shop
 */
public abstract class ShopService extends Entity {
    private String name;
    private Money price;
    private PriceType priceType;
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

    @Override
    public ValidationResult validate() {
        List<String> errors = new ArrayList<>();
        if(getName() == null || getName().isEmpty()){
            errors.add("Name is empty");
        }
        if(getPrice() == null || !getPrice().hasValue()){
            errors.add("Price is not set");
        }
        return new ValidationResult(errors);
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }
}
