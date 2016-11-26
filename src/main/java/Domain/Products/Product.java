package Domain.Products;

import Domain.Common.Money;
import Domain.Services.ShopService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Product extends ShopService {
    private Picture picture;
    private Category category;
    private ProductType productType;
    private Collection<ProductFeature> productFeatures;
    private Collection<Discount> productDiscounts;

    public Product() {
        productFeatures = new ArrayList<>();
        productDiscounts = new ArrayList<>();
    }

    public Product(String name, Money price) {
        super(name, price);
        productFeatures = new ArrayList<>();
        productDiscounts = new ArrayList<>();
    }

    public Product(String name, Money price, List<ShopService> services,
                   Collection<ProductFeature> features, Collection<Discount> productDiscounts) {
        super(name, price, services);
        productFeatures = features;
        this.productDiscounts = productDiscounts;
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

    public void setCategory(Category category) {
        this.category = category;
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
}
