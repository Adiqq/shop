package Domain.Repositories;

import Domain.ShopService;
import Infrastructure.Error;
import Infrastructure.*;
import Persistance.IStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Adiq on 16.10.2016.
 */
public class ShopServicesRepository extends Repository<ShopService> {

    public ShopServicesRepository(IStore<ShopService> store) {
        super(store);
    }

    public Response<Iterable<ShopService>> GetServices() {
        Response<List<ShopService>> response = getResponse();
        return new Response<>(response.getResult(), response.getContent());
    }

    public Response<ShopService> GetServiceById(UUID id) {
        Response<List<ShopService>> response = getResponse();
        if (response.getResult().isSuccess()) {
            Optional<ShopService> result = response
                    .getContent()
                    .stream()
                    .filter(item -> item.getId() == id).findFirst();
            if (result.isPresent()) {
                return new Response<>(new OkResult(), result.get());
            } else {
                return new Response<>(new FailResult(Error.IdNotFound), null);
            }
        } else {
            return new Response<>(new FailResult(response.getResult().getDescription()), null);
        }
    }

    public Response<ShopService> GetServiceByName(String name) {
        Response<List<ShopService>> response = getResponse();
        if (response.getResult().isSuccess()) {
            Optional<ShopService> result = response
                    .getContent()
                    .stream()
                    .filter(item -> item.getName().equalsIgnoreCase(name)).findFirst();
            if (result.isPresent()) {
                return new Response<>(new OkResult(), result.get());
            } else {
                return new Response<>(new FailResult(Error.IdNotFound), null);
            }
        } else {
            return new Response<>(new FailResult(response.getResult().getDescription()), null);
        }
    }

    public Response<ShopService> AddService(ShopService service) {
        Response<List<ShopService>> response = getResponse();
        if (response.getResult().isSuccess()) {
            UUID id = UUID.randomUUID();
            service.setId(id);
            items.add(service);
            return new Response<>(new OkResult(), service);
        }
        return new Response<>(new FailResult(response.getResult().getDescription()), service);

    }

    public Result RemoveService(ShopService service) {
        Response<List<ShopService>> response = getResponse();
        if (response.getResult().isSuccess()) {
            Optional<ShopService> result = items.stream().filter(item -> item.getId() == service.getId()).findFirst();
            if (result.isPresent()) {
                if (items.remove(result.get())) {
                    return new OkResult();
                } else {
                    return new FailResult(Error.IdNotFound);
                }
            } else {
                return new FailResult(Error.IdNotFound);
            }
        }
        return new FailResult(response.getResult().getDescription());
    }

    public Result UpdateService(ShopService service) {
        if (RemoveService(service).isSuccess()) {
            items.add(service);
            return new OkResult();
        }

        return new FailResult(Error.IdNotFound);
    }
}