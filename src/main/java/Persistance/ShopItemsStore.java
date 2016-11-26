package Persistance;

import Domain.ShopItems;

/**
 * Created by Adiq on 20.11.2016.
 */
public class ShopItemsStore extends StoreSerializer<ShopItems> {
    private static final String filename = "ShopItems.xml";

    public ShopItemsStore() {
        super(filename);
        initialize();
    }

    private void initialize() {
        try {
            if (!super.deserialize().getResult().isSuccess()) {
                super.serialize(new ShopItems());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
