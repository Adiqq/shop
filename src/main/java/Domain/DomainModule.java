package Domain;

import Domain.Repositories.ShopItemsRepository;
import Infrastructure.Repository;
import Persistance.PersistenceModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class DomainModule extends AbstractModule {
    protected void configure() {
        install(new PersistenceModule());
        bind(new TypeLiteral<Repository<ShopItems>>() {
        }).to(ShopItemsRepository.class);
    }
}
