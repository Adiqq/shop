package Domain.Products;

import Domain.Common.Money;
import Domain.Services.InstrumentMaintenance;
import Domain.Services.InstrumentPlayLessons;
import Domain.Services.ShopService;

import java.util.ArrayList;

/**
 *  Guitar object with predefined services
 */
public class Gitara extends Product {
    public Gitara() {
        super(Category.Gitara);
        ArrayList<ShopService> shopServices = new ArrayList<>();
        shopServices.add(new InstrumentPlayLessons("Nauka gry na gitarze", new Money(20l)));
        shopServices.add(new InstrumentMaintenance("Wymiana przystawek", new Money(300l)));
        shopServices.add(new InstrumentMaintenance("Ustawienie menzury", new Money(100l)));
        shopServices.add(new InstrumentMaintenance("Nabicie progów", new Money(150l)));
        setServices(shopServices);
    }

    public Gitara(String name, Money price) {
        super(Category.Gitara, name, price);
    }

}
