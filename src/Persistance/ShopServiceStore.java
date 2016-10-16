package Persistance;

import Domain.ShopService;
import Infrastructure.Response;
import Infrastructure.Result;

import java.util.List;

/**
 * Created by Adiq on 16.10.2016.
 */
public class ShopServiceStore extends StoreSerializer implements IStore<ShopService> {
    private static final String filename = "ShopServiceStore.xml";

    public ShopServiceStore() {
        super(filename);
    }

    public Response<List<ShopService>> deserializeStore() {
        return super.deserialize();
    }

    public Result serializeStore(List<ShopService> services) {
        return super.serialize(services);
    }
}
