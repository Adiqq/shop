package Persistance;

import Domain.ShopItems;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class PersistenceModule extends AbstractModule {
    protected void configure() {
        bind(new TypeLiteral<IStore<ShopItems>>() {
        }).to(ShopItemsStore.class);
    }
}
