package UI.Model;

import Domain.Products.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductViewModel extends ShopServiceViewModel {
    private final ObjectProperty<Category> category;
    private final ObjectProperty<ProductType> productType;
    private Product product;
    private ObservableList<DiscountViewModel> discounts;
    private ObservableList<FeatureViewModel> features;

    public ProductViewModel(Product product){
        super(product);
        this.category = new SimpleObjectProperty<>();
        this.productType = new SimpleObjectProperty<>();
        this.product = product;
        Category category = product.getCategory();
        if(category != null){
            this.category.setValue(category);
        }
        ProductType productType = product.getProductType();
        if(productType != null && category != null){
            Optional<ProductType> productTypeOptional = category.getProductTypes().stream()
                    .filter(x -> x.getType().equals(productType.getType())).findFirst();
            this.productType.setValue(productTypeOptional.get());
        }
        Collection<Discount> discounts = product.getProductDiscounts();
        if(discounts != null){
            this.discounts = FXCollections.observableArrayList(
                    discounts
                            .stream()
                            .map(DiscountViewModel::new)
                            .collect(Collectors.toList())
                            );
            this.discounts.addListener((ListChangeListener<DiscountViewModel>) c ->
                    product.setProductDiscounts(c
                                    .getList()
                                    .stream()
                                    .map(x -> new Discount(
                                            new DiscountPercentage(x.getPercentage().getValue()),
                                            x.getBeginDate(),
                                            x.getEndDate()))
                                    .collect(Collectors.toList())
            ));
        }
        Collection<ProductFeature> features = product.getProductFeatures();
        if(features != null){
            this.features = FXCollections.observableArrayList(
                    features
                            .stream()
                            .map(FeatureViewModel::new)
                            .collect(Collectors.toList())
            );
            this.features.addListener((ListChangeListener<FeatureViewModel>) c ->
                    product.setProductFeatures(c
                            .getList()
                            .stream()
                            .map(x -> x.getFeature())
                            .collect(Collectors.toList())
                    ));
        }
        this.productType.addListener((observable, oldValue, newValue) -> product.setProductType(newValue));
    }

    public ObjectProperty<Category> getCategoryProperty() {
        return category;
    }

    public ObjectProperty<ProductType> getProductTypeProperty() {
        return productType;
    }

    public Product getProduct() {
        return product;
    }

    public Image getImage(){
        Picture picture = getProduct().getPicture();

        if(picture != null){
            byte[] content = picture.getContentAsByteArray();
            if(content != null) {
                Image image = new Image(new ByteArrayInputStream(content));
                return image;
            }
        }
        return null;
    }

    public ObservableList<DiscountViewModel> getDiscounts() {
        return discounts;
    }
    public ObservableList<FeatureViewModel> getFeatures() {
        return features;
    }
}
