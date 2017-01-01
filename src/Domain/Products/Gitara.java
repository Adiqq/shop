package Domain.Products;

import Domain.Common.Money;

public class Gitara extends Product {
    public Gitara() {
        super(Category.Gitara);
    }

    public Gitara(String name, Money price) {
        super(Category.Gitara, name, price);
    }

}
