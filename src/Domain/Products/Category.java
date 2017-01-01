package Domain.Products;

import java.util.Arrays;
import java.util.List;

public class Category {
    public static final Category Gitara = new Category("Gitara",
            Arrays.asList(
                    new ProductType("Elektryczna"),
                    new ProductType("Akustyczna")));
    public static final Category InstrumentPerkusyjny = new Category("Instrument perkusyjny",
            Arrays.asList(
                    new ProductType("Żywy"),
                    new ProductType("Trup")));
    public static final Category InstrumentKlawiszowy = new Category("Instrument klawiszowy",
            Arrays.asList(
                    new ProductType("Młoteczkowy"),
                    new ProductType("Elektroniczny")));
    public static final Category SprzetNaglosnieniowy = new Category("Sprzęt nagłośnieniowy",
            Arrays.asList(
                    new ProductType("Konsola"),
                    new ProductType("Głośnik"),
                    new ProductType("Mikrofon")));
    private String category;
    private List<ProductType> productTypes;

    public Category() {
    }

    public Category(String category, List<ProductType> productTypes) {
        this.category = category;
        this.productTypes = productTypes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public String toString() {
        return getCategory();
    }
}
