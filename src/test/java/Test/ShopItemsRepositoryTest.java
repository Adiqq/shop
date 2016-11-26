package Test;

import Domain.Repositories.ShopItemsRepository;
import Domain.ShopItems;
import Infrastructure.Repository;
import Infrastructure.Response;
import Infrastructure.Result;
import Persistance.IStore;
import Persistance.ShopItemsStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ShopItemsRepositoryTest {
    Repository<ShopItems> repository;

    @Before
    public void setUp() throws Exception {
        IStore<ShopItems> store = new ShopItemsStore();
        repository = new ShopItemsRepository(store);
    }

    @Test
    public void loadsStore() {
        Response<ShopItems> items = repository.get();
        assertTrue(items.getResult().isSuccess());
    }

    @Test
    public void saveStore() {
        ShopItems items = new ShopItems();
        Result result = repository.save(items);
        assertTrue(result.isSuccess());
    }

}