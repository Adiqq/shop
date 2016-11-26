package Service;

import Domain.Products.Category;
import Domain.Repositories.ShopItemsRepository;
import Infrastructure.OkResult;
import Infrastructure.Result;
import UI.Model.ProductViewModel;
import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ShopItemsService {
    private ShopItemsRepository shopServicesRepository;

    @Inject
    public ShopItemsService(ShopItemsRepository shopServicesRepository) {
        this.shopServicesRepository = shopServicesRepository;
    }

    public List<ShopServiceViewModel> getShopServices() {
        List<ShopServiceViewModel> list = shopServicesRepository
                .get()
                .getContent()
                .getServices()
                .stream()
                .map(shopService -> new ShopServiceViewModel(shopService.getId().toString(), shopService.getName(), shopService.getPrice().toString()))
                .collect(Collectors.toList());
        return list;
    }

    public List<ProductViewModel> getProducts() {
        List<ProductViewModel> list = shopServicesRepository
                .get()
                .getContent()
                .getProducts()
                .stream()
                .map(shopService ->
                        new ProductViewModel(
                                shopService.getId().toString(),
                                shopService.getName(),
                                shopService.getPrice().toString(),
                                shopService.getCategory().getCategory(),
                                shopService.getProductType().toString()
                        ))
                .collect(Collectors.toList());
        return list;
    }

    public Collection<Category> getCategories() {
        Collection<Category> collection = shopServicesRepository
                .get()
                .getContent()
                .getProductCategories();
        return collection;
    }

    public Result addShopService(ShopServiceViewModel vm) {
        return new OkResult();
    }

    public Result editShopService(ShopServiceViewModel vm) {
        return new OkResult();
    }

    public Result deleteShopService(ShopServiceViewModel vm) {
        return new OkResult();
    }
}
