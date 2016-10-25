package UI.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShopServiceViewModel {

    private final StringProperty name;
    private final StringProperty price;
    private String id;
    public ShopServiceViewModel() {
        this(null, "", "");
    }

    public ShopServiceViewModel(String id, String name, String price) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
