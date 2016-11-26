package UI.Controller.Admin.Products;

import Domain.Products.Category;
import UI.Controller.ModalController;
import UI.Model.ProductViewModel;
import UI.Model.ShopServiceViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProductEditDialogController extends ModalController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox categoryChoice;
    @FXML
    private ChoiceBox productTypeChoice;
    private ObservableList<String> categoriesList;
    private ObservableList<String> productTypes;
    private Collection<Category> categories;

    private Stage dialogStage;

    private ShopServiceViewModel shopService;

    public void setCategories(Collection<Category> categories) {
        categoriesList = FXCollections.observableList(
                categories.stream().map(x -> toString()).collect(Collectors.toList())
        );
        productTypes = FXCollections.observableList(new ArrayList<>());
        categoryChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setProductTypeList(newValue.toString())
        );
        categoryChoice.setItems(categoriesList);
        productTypeChoice.setItems(productTypes);
    }

    private void setProductTypeList(String newValue) {
        productTypes.clear();
        productTypes.addAll(categories.stream().filter(
                x -> x.getCategory().equals(newValue)).findFirst().get()
                .getProductTypes().stream().map(x -> x.getType().toString()).collect(Collectors.toList()));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setShopService(ProductViewModel shopService) {
        this.shopService = shopService;

        nameField.setText(shopService.getName());
        priceField.setText(shopService.getPrice());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            shopService.setName(nameField.getText());
            shopService.setPrice(priceField.getText());

            super.okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);

        alert.showAndWait();

        return false;
    }

}
