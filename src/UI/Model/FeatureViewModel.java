package UI.Model;

import Domain.Products.ProductFeature;
import javafx.beans.property.SimpleStringProperty;

public class FeatureViewModel {
    private ProductFeature feature;

    public String getNameProperty() {
        return nameProperty.get();
    }

    public SimpleStringProperty namePropertyProperty() {
        return nameProperty;
    }

    public String getValueProperty() {
        return valueProperty.get();
    }

    public SimpleStringProperty valuePropertyProperty() {
        return valueProperty;
    }

    private SimpleStringProperty nameProperty;
    private SimpleStringProperty valueProperty;

    public FeatureViewModel(ProductFeature feature){
        this.feature = feature;
        nameProperty = new SimpleStringProperty();
        valueProperty = new SimpleStringProperty();
        if(feature.getName() != null){
            nameProperty.setValue(feature.getName());
        }
        if(feature.getValue() != null){
            valueProperty.setValue(feature.getValue());
        }
        nameProperty.addListener((observable, oldValue, newValue) -> feature.setName(newValue));
        valueProperty.addListener((observable, oldValue, newValue) -> feature.setValue(newValue));
    }

    public ProductFeature getFeature() {
        return feature;
    }
}
