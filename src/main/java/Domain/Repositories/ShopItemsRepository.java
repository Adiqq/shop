package Domain.Repositories;

import Domain.ShopItems;
import Infrastructure.Repository;
import Persistance.IStore;
import com.google.inject.Inject;

public class ShopItemsRepository extends Repository<ShopItems> {
    @Inject
    public ShopItemsRepository(IStore<ShopItems> store) {
        super(store);
    }
}
