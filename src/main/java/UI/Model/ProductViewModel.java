package UI.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductViewModel extends ShopServiceViewModel {
    private final StringProperty category;
    private final StringProperty productType;

    public ProductViewModel() {
        this("", "", "", "", "");
    }

    private ProductViewModel(String category, String productType) {
        this("", "", "", category, productType);
    }

    public ProductViewModel(String id, String name, String price, String category, String productType) {
        super(id, name, price);
        this.category = new SimpleStringProperty(category);
        this.productType = new SimpleStringProperty(productType);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getProductType() {
        return productType.get();
    }

    public void setProductType(String value) {
        productType.set(value);
    }

    public StringProperty productTypeProperty() {
        return productType;
    }
}
