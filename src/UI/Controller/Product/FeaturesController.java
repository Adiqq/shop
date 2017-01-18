package UI.Controller.Product;

import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.DiscountViewModel;
import UI.Model.FeatureViewModel;
import UI.Model.ProductViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller responsible for displaying features for selected product
 */
public class FeaturesController extends TabController {
    private ProductViewModel productViewModel;
    @FXML
    public TableColumn<FeatureViewModel, String> name;
    @FXML
    public TableColumn<FeatureViewModel, String> value;
    @FXML
    public TableView<FeatureViewModel> featuresTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setProductViewModel(ProductViewModel vm) {
        productViewModel = vm;
        featuresTable.setItems(vm.getFeatures());

        name.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        value.setCellValueFactory(cellData -> cellData.getValue().valuePropertyProperty());
    }

    public void add(ActionEvent actionEvent) {
        TabMediator.createFeature(productViewModel);
    }

    public void edit(ActionEvent actionEvent) {
        FeatureViewModel selectedItem = featuresTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TabMediator.editFeature(productViewModel, selectedItem);
        }
    }

    public void delete(ActionEvent actionEvent) {
        FeatureViewModel selectedItem = featuresTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            productViewModel.getFeatures().remove(selectedItem);
        }
    }
}
