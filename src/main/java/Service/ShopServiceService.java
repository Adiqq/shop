package Service;

import Domain.Money;
import Domain.Repositories.ShopServicesRepository;
import Domain.ShopService;
import Infrastructure.FailResult;
import Infrastructure.OkResult;
import Infrastructure.Response;
import Infrastructure.Result;
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

    public Result addShopService(ShopServiceViewModel vm) {
        try {
            ShopService service = new ShopService();
            service.setName(vm.getName());
            service.setPrice(new Money(vm.getPrice()));
            Response<ShopService> result = shopServicesRepository.AddService(service);
            Result saveResult = shopServicesRepository.Save();
            if (result.getResult().isSuccess() && saveResult.isSuccess()) {
                vm.setId(result.getContent().getId());
                vm.setName(result.getContent().getName());
                vm.setPrice(result.getContent().getPrice().toString());
                return new OkResult();
            }
        } catch (Exception e) {
            return new FailResult("Could not add shop service");
        }
        return new FailResult("Could not add shop service");
    }

    public Result editShopService(ShopServiceViewModel vm) {
        try {
            ShopService service = shopServicesRepository.GetServiceById(vm.getId()).getContent();
            service.setPrice(new Money(vm.getPrice()));
            service.setName(vm.getName());
            Result updateResult = shopServicesRepository.UpdateService(service);
            Result saveResult = shopServicesRepository.Save();
            Response<ShopService> model = shopServicesRepository.GetServiceById(vm.getId());
            vm.setPrice(model.getContent().getPrice().toString());
            vm.setName(model.getContent().getName());
            if (model.getResult().isSuccess() && updateResult.isSuccess() && saveResult.isSuccess()) {
                return new OkResult();
            }

        } catch (Exception e) {
        }

        return new FailResult("Could not edit shop service");
    }

    public Result deleteShopService(ShopServiceViewModel vm) {
        Result removeResult = shopServicesRepository.RemoveServiceById(vm.getId());
        Result saveResult = shopServicesRepository.Save();
        if (removeResult.isSuccess() && saveResult.isSuccess()) {
            return new OkResult();
        }
        return new FailResult("Could not delete shop service");
    }
}
