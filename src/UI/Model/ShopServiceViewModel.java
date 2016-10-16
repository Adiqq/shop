package UI.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShopServiceViewModel {
    private final StringProperty name;
    private final StringProperty price;

    public ShopServiceViewModel(String name, String price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getPrice() {
        return price;
    }
}
