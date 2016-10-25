package Persistance;

import Domain.ShopService;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class PersistenceModule extends AbstractModule {
    protected void configure() {
        bind(new TypeLiteral<IStore<ShopService>>() {
        }).to(ShopServiceStore.class);
    }
}
