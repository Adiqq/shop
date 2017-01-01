package UI.Model;

import Domain.Products.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class ProductOptionsViewModel {
    private ObservableList<Category> categories;

    public ProductOptionsViewModel(Collection<Category> categories) {
        setCategories(FXCollections.observableArrayList(categories));
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ObservableList<Category> categories) {
        this.categories = categories;
    }
}
