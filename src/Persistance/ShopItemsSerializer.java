package Persistance;

import Domain.ShopItems;

public class ShopItemsSerializer extends Serializer<ShopItems> {
    public ShopItemsSerializer() {
        super("ShopItems.xml");
    }
}
