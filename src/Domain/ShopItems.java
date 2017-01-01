package Domain;

import Domain.Products.Category;
import Domain.Products.Product;
import Domain.Services.ShopService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class ShopItems {
    private Collection<ShopService> services;
    private Collection<Product> products;
    private Collection<Category> productCategories;

    public ShopItems() {
        services = new HashSet<>();
        products = new HashSet<>();
        productCategories = new HashSet<Category>() {
            {
                add(Category.Gitara);
                add(Category.InstrumentKlawiszowy);
                add(Category.InstrumentPerkusyjny);
                add(Category.SprzetNaglosnieniowy);
            }
        };
    }

    public Collection<ShopService> getServices() {
        return services;
    }

    public void setServices(Collection<ShopService> services) {
        this.services = services;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Optional<Product> getProduct(String id){
        return getProducts().stream().filter(x -> x.getId().toString().equals(id)).findFirst();
    }

    public void deleteProduct(Product product){
        getProducts().removeIf(x -> x.getId().toString().equals(product.getId().toString()));
    }

    public void saveProduct(Product product){
        deleteProduct(product);
        getProducts().add(product);
    }

    public Collection<Category> getProductCategories() {
        return productCategories;
    }
}
