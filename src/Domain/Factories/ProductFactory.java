package Domain.Factories;

import Domain.Products.*;

/**
 *  Product factory for creating appropriate product, based on category
 */
public class ProductFactory {
    /**
     *
     * @param category Product category
     * @return Product
     */
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
