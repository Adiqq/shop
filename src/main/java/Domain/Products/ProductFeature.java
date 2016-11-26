package Domain.Products;

/**
 * Created by Adiq on 26.11.2016.
 */
public class ProductFeature {
    private String name;
    private String value;

    public ProductFeature() {
    }

    public ProductFeature(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
