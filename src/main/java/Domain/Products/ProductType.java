package Domain.Products;

/**
 * Created by Adiq on 20.11.2016.
 */
public class ProductType {
    private String type;

    public ProductType() {
    }

    public ProductType(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType();
    }
}
