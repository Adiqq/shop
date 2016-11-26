package Domain.Products;

import Domain.Common.Money;

public class Gitara extends Product {
    public Gitara() {
        setCategory(Category.Gitara);
    }

    public Gitara(String name, Money price) {
        super(name, price);
    }

}
