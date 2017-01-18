package Domain.Products;

import Domain.Common.Money;
import Domain.Infrastructure.ValidationResult;
import Domain.Services.ShopService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  Abstract class for products
 */
public abstract class Product extends ShopService {
    private Picture picture;
    private final Category category;
    private ProductType productType;
    private Collection<ProductFeature> productFeatures;
    private Collection<Discount> productDiscounts;

    public Product(Category category) {
        productFeatures = new ArrayList<>();
        productDiscounts = new ArrayList<>();
        this.category = category;
    }

    public Product(Category category, String name, Money price) {
        super(name, price);
        productFeatures = new ArrayList<>();
        productDiscounts = new ArrayList<>();
        this.category = category;
    }

    public Product(Category category, String name, Money price, List<ShopService> services,
                   Collection<ProductFeature> features, Collection<Discount> productDiscounts) {
        super(name, price, services);
        productFeatures = features;
        this.productDiscounts = productDiscounts;
        this.category = category;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Collection<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(Collection<ProductFeature> productFeatures) {
        this.productFeatures = productFeatures;
    }

    public Collection<Discount> getProductDiscounts() {
        return productDiscounts;
    }

    public void setProductDiscounts(Collection<Discount> productDiscounts) {
        this.productDiscounts = productDiscounts;
    }

    @Override
    public ValidationResult validate() {
        List<String> errors = new ArrayList<>();
        if(getName() == null || getName().isEmpty()){
            errors.add("Name is empty");
        }
        if(getPrice() == null || !getPrice().hasValue()){
            errors.add("Price is not set");
        }
        if(getCategory() == null || getCategory().getCategory() == null || getCategory().getCategory().isEmpty()){
            errors.add("Category is not set");
        }
        if(getProductType() == null || getProductType().getType() == null || getProductType().getType().isEmpty()){
            errors.add("Product type is not set");
        }

        return new ValidationResult(errors);
    }
}
