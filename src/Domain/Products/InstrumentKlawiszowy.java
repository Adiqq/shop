package Domain.Products;

import Domain.Common.Money;
import Domain.Services.InstrumentMaintenance;
import Domain.Services.InstrumentPlayLessons;
import Domain.Services.ShopService;

import java.util.ArrayList;

/**
 * Keyboard instrument with predefined services
 */
public class InstrumentKlawiszowy extends Product {
    public InstrumentKlawiszowy() {

        super(Category.InstrumentKlawiszowy);
        ArrayList<ShopService> shopServices = new ArrayList<>();
        shopServices.add(new InstrumentMaintenance("Strojenie", new Money(50l)));
        setServices(shopServices);
    }

    public InstrumentKlawiszowy(String name, Money price) {
        super(Category.InstrumentKlawiszowy,name, price);
    }
}
