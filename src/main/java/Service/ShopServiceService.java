package Service;

import Domain.Money;
import Domain.Repositories.ShopServicesRepository;
import Domain.ShopService;
import Infrastructure.Response;
import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class ShopServiceService {
    private ShopServicesRepository shopServicesRepository;

    @Inject
    public ShopServiceService(ShopServicesRepository shopServicesRepository) {
        this.shopServicesRepository = shopServicesRepository;
    }

    public List<ShopServiceViewModel> getShopServices() {
        List<ShopServiceViewModel> list = shopServicesRepository
                .GetServices()
                .getContent()
                .stream()
                .map(shopService -> new ShopServiceViewModel(shopService.getId(), shopService.getName(), shopService.getPrice().getValue()))
                .collect(Collectors.toList());
        return list;
    }

    public void addShopService(ShopServiceViewModel vm) {
        ShopService service = new ShopService();
        service.setName(vm.getName());
        service.setPrice(new Money(vm.getPrice()));
        Response<ShopService> result = shopServicesRepository.AddService(service);
        vm.setId(result.getContent().getId());
        shopServicesRepository.Save();
    }

    public void editShopService(ShopServiceViewModel vm) {
        ShopService service = shopServicesRepository.GetServiceById(vm.getId()).getContent();
        service.setPrice(new Money(vm.getPrice()));
        service.setName(vm.getName());
        shopServicesRepository.UpdateService(service);
        shopServicesRepository.Save();
    }

    public void deleteShopService(ShopServiceViewModel vm) {
        shopServicesRepository.RemoveServiceById(vm.getId());
        shopServicesRepository.Save();
    }
}
