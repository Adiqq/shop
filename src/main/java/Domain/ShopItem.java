package Domain;

import Domain.ShopItemType.ShopItemType;

import java.util.List;

public class ShopItem extends ShopService {
    public Category category;
    public Picture picture;
    private ShopItemType itemType;
    private List<ShopService> services;
}
