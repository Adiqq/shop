package UI.Controller;

import UI.Model.ShopServiceViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShopServiceOverviewController {
    @FXML
    private TableView<ShopServiceViewModel> shopServicesTable;

    @FXML
    private TableColumn<ShopServiceViewModel, String> nameColumn;
    @FXML
    private TableColumn<ShopServiceViewModel, String> priceColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    private MainController mainApp;

    public void setMainApp(MainController mainApp) {
        this.mainApp = mainApp;
        shopServicesTable.setItems(mainApp.getShopServiceData());
    }

    private void showShopServiceDetials(ShopServiceViewModel vm) {
        if (vm != null) {
            nameLabel.setText(vm.getName());
            priceLabel.setText(vm.getPrice());
        } else {
            nameLabel.setText("");
            priceLabel.setText("");
        }
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

        showShopServiceDetials(null);
        shopServicesTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showShopServiceDetials(newValue))
        );

    }

    @FXML
    private void handleDeleteShopService() {
        int selectedIndex = shopServicesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            shopServicesTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No shop service selected");
            alert.setContentText("Please select shop service in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewShopService() {
        ShopServiceViewModel shopService = new ShopServiceViewModel();
        boolean okClicked = mainApp.showShopServiceEditDialog(shopService);

        if (okClicked) {
            mainApp.getShopServiceData().add(shopService);
        }
    }

    @FXML
    private void handleEditShopService() {
        ShopServiceViewModel shopService = shopServicesTable.getSelectionModel().getSelectedItem();
        if (shopService != null) {
            boolean okClicked = mainApp.showShopServiceEditDialog(shopService);
            if (okClicked) {
                showShopServiceDetials(shopService);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No shop service selected");
            alert.setContentText("Please select a shop service in the table.");

            alert.showAndWait();
        }
    }
}
