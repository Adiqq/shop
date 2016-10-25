package UI.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShopServiceViewModel {
    private final StringProperty name;
    private final StringProperty price;

    public ShopServiceViewModel() {
        this("", "");
    }

    public ShopServiceViewModel(String name, String price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty getPriceProperty() {
        return price;
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String value) {
        price.set(value);
    }
}
