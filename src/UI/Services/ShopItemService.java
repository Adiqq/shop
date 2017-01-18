package UI.Services;

import Domain.Common.Money;
import Domain.Products.Gitara;
import Domain.Products.Product;
import Domain.ShopItems;
import Persistance.ShopItemsSerializer;
import UI.Model.ProductOptionsViewModel;
import UI.Model.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for retrieving and persisting ShopItems object
 */
public class ShopItemService {
    private ObservableList<ProductViewModel> allProducts;
    private ProductOptionsViewModel productOptions;
    private ShopItemsSerializer serializer;
    private ShopItems shopItems;

    public ShopItemService(){
        serializer = new ShopItemsSerializer();
        shopItems = serializer.deserialize();
        if(shopItems == null){
            shopItems = new ShopItems();
        }
        List<ProductViewModel> products = shopItems
                .getProducts()
                .stream()
                .map(x -> new ProductViewModel(x))
                .collect(Collectors.toList());
        allProducts = FXCollections.observableList(products);
        productOptions = new ProductOptionsViewModel(shopItems.getProductCategories());
    }

    public void delete(ProductViewModel model) {
        allProducts.remove(model);
        shopItems.deleteProduct(model.getProduct());
        serializer.serialize(shopItems);
    }

    public void save(ProductViewModel model) {
            allProducts.removeIf(x -> x.getId().toString().equals(model.getId().toString()));
            allProducts.add(model);
            shopItems.saveProduct(model.getProduct());
            serializer.serialize(shopItems);
    }

    public ObservableList<ProductViewModel> getAllProducts() {
        return allProducts;
    }

    public ProductOptionsViewModel getProductOptions() {
        return productOptions;
    }
}
