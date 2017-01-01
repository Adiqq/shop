package UI.Controller.Product;

import Domain.Factories.ProductFactory;
import Domain.Products.Category;
import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.ProductOptionsViewModel;
import UI.Model.ProductViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailsController extends TabController {
    @FXML
    public Label nameLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public ImageView imageView;
    private ProductViewModel model;

    public void create(){
        showCategoryDialog().ifPresent(x -> TabMediator.createProductTab(ProductFactory.createProduct(x)));
    }
    public void edit(){ TabMediator.editProductTab();}
    public void delete() {TabMediator.deleteProduct();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public ProductViewModel getModel() {
        return model;
    }

    public void SetSelectionListener(ReadOnlyObjectProperty<ProductViewModel> selectionProperty){
        selectionProperty.addListener(
                ((observable, oldValue, newValue) -> {
                    model = newValue;
                    nameLabel.setText(model.getNameProperty().getValue());
                    priceLabel.setText(model.getPriceProperty().getValue());
                    imageView.setImage(model.getImage());
                })
        );
    }

    private Optional<Category> showCategoryDialog(){
        ProductOptionsViewModel productOptionsViewModel = TabMediator.getProductOptionsViewModel();

        ChoiceDialog<Category> dialog = new ChoiceDialog<>(Category.Gitara, productOptionsViewModel.getCategories());
        dialog.setTitle("Select category");
        dialog.setHeaderText("Pick your instrument:");
        return dialog.showAndWait();
    }
}
