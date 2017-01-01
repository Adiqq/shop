package Domain.Factories;

import Domain.Products.*;

public class ProductFactory {
    public static Product createProduct(Category category){
        if(category == Category.Gitara){
            return new Gitara();
        }
        if(category == Category.InstrumentKlawiszowy){
            return new InstrumentKlawiszowy();
        }
        if(category == Category.InstrumentPerkusyjny){
            return new InstrumentPerkusyjny();
        }
        if(category == Category.SprzetNaglosnieniowy){
            return new SprzetNaglosnieniowy();
        }
        return null;
    }
}
