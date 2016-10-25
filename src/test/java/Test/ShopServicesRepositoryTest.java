package Test;

import Domain.Money;
import Domain.Repositories.ShopServicesRepository;
import Domain.ShopService;
import Infrastructure.Response;
import Infrastructure.Result;
import Persistance.ShopServiceStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adiq on 16.10.2016.
 */
public class ShopServicesRepositoryTest {

    ShopServicesRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new ShopServicesRepository(new ShopServiceStore());
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void getServices() throws Exception {
        Response<List<ShopService>> result = repository.GetServices();

        assertTrue(result.getResult().isSuccess());
        assertNotNull(result.getContent());
        //should be empty
        for (ShopService item : result.getContent()) {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    public void getServiceById() throws Exception {
        //Arrange
        ShopService service = getShopService();
        //Act
        ShopService result = repository.AddService(service).getContent();
        ShopService result2 = repository.GetServiceById(result.getId()).getContent();
        //Assert
        assertTrue(result.getId() == result2.getId());
    }

    @Test
    public void getServiceByName() throws Exception {
        //Arrange
        ShopService service = getShopService();
        //Act
        ShopService result = repository.AddService(service).getContent();
        ShopService result2 = repository.GetServiceByName(result.getName().toLowerCase()).getContent();
        //Assert
        assertTrue(result.getId() == result2.getId());
    }

    @Test
    public void addService() throws Exception {
        //Arrange
        ShopService service = getShopService();
        //Act
        ShopService result = repository.AddService(service).getContent();
        Result saveResult = repository.Save();
        //Assert
        assertNotNull(result);
        assertTrue(saveResult.isSuccess());
    }

    @Test
    public void removeService() throws Exception {
        //Arrange
        ShopService service = getShopService();
        //Act
        ShopService result = repository.AddService(service).getContent();
        Result saveResult = repository.Save();
        //Assert
        assertTrue(repository.RemoveService(service).isSuccess());
        assertTrue(saveResult.isSuccess());
    }

    @Test
    public void updateService() throws Exception {
        //Arrange
        ShopService service = getShopService();
        //Act
        ShopService result = repository.AddService(service).getContent();
        String newName = "newTest";
        ShopService result2 = repository.GetServiceById(result.getId()).getContent();
        result2.setName(newName);
        Result updateResult = repository.UpdateService(result2);
        ShopService finalResult = repository.GetServiceById(result.getId()).getContent();
        Result saveResult = repository.Save();
        //Assert
        assertTrue(updateResult.isSuccess());
        assertTrue(saveResult.isSuccess());
        assertTrue(finalResult.getId() == result.getId());

    }

    private ShopService getShopService() {
        ShopService service = new ShopService();
        service.setName("Test");
        Money money = new Money("100");
        service.setPrice(money);
        return service;
    }

}