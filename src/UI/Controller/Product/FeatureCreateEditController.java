package UI.Controller.Product;

import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.FeatureViewModel;
import UI.Model.ProductViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FeatureCreateEditController extends TabController {
    private ProductViewModel productViewModel;
    private FeatureViewModel featureViewModel;
    @FXML
    public TextField nameField;
    @FXML
    public TextField valueField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveAndClose(ActionEvent actionEvent) {
        if(!productViewModel.getFeatures().contains(featureViewModel)){
            productViewModel.getFeatures().add(featureViewModel);
        }
        TabMediator.closeTab(getTab());
    }

    public void setModel(ProductViewModel productViewModel, FeatureViewModel featureViewModel) {
        this.productViewModel = productViewModel;
        this.featureViewModel = featureViewModel;
        nameField.textProperty().bindBidirectional(featureViewModel.namePropertyProperty());
        valueField.textProperty().bindBidirectional(featureViewModel.valuePropertyProperty());
    }
}
