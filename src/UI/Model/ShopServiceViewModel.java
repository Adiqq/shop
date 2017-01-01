package UI.Model;

import Domain.Common.Money;
import Domain.Services.InstrumentHandling;
import Domain.Services.ShopService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class ShopServiceViewModel {

    private final StringProperty name;
    private final StringProperty price;
    private final StringProperty id;
    private ShopService service;
    public ShopServiceViewModel(ShopService service){
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        this.service = service;
        id.setValue(service.getId().toString());
        if(service.getName() != null) {
            name.setValue(service.getName());
        }
        if(service.getPrice() != null) {
            price.setValue(service.getPrice().toString());
        }
        id.addListener((observable, oldValue, newValue) -> service.setId(UUID.fromString(newValue)));
        name.addListener((observable, oldValue, newValue) -> service.setName(newValue));
        price.addListener((observable, oldValue, newValue) -> service.setPrice(new Money(newValue)));
    }

    public StringProperty getNameProperty() {
        return name;
    }
    public StringProperty getPriceProperty() {
        return price;
    }
    public StringProperty idProperty() {return id;}
    public String getId() {return id.get();}


}
