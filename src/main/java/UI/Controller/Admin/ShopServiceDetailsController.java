package UI.Controller.Admin;

import Infrastructure.Result;
import Service.ShopServiceService;
import UI.Controller.Controller;
import UI.Model.ShopServiceViewModel;
import UI.Services.AlertService;
import UI.Services.Modal;
import UI.Services.ViewLoader;
import com.google.inject.Inject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ShopServiceDetailsController extends Controller {

    private AlertService alertService;
    private ShopServiceService shopServiceService;
    private ViewLoader viewLoader;
    private TableView<ShopServiceViewModel> shopServicesTable;
    private ObservableList<ShopServiceViewModel> shopServiceData;

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;

    @Inject
    public ShopServiceDetailsController(AlertService alertService, ShopServiceService shopServiceService, ViewLoader viewLoader) {

        this.alertService = alertService;
        this.shopServiceService = shopServiceService;
        this.viewLoader = viewLoader;
    }

    void init(TableView<ShopServiceViewModel> shopServicesTable, ObservableList<ShopServiceViewModel> shopServiceData) {
        this.shopServicesTable = shopServicesTable;
        this.shopServiceData = shopServiceData;
        showShopServiceDetails(null);
        shopServicesTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showShopServiceDetails(newValue))
        );
    }


    private void showShopServiceDetails(ShopServiceViewModel vm) {
        if (vm != null) {
            nameLabel.setText(vm.getName());
            priceLabel.setText(vm.getPrice());
        } else {
            nameLabel.setText("");
            priceLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteShopService() {
        int selectedIndex = shopServicesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            ShopServiceViewModel vm = shopServicesTable.getItems().get(selectedIndex);
            Result deleteResult = shopServiceService.deleteShopService(vm);
            if (deleteResult.isSuccess()) {
                shopServicesTable.getItems().remove(selectedIndex);
            } else {
                handleDeleteShopServiceFail(deleteResult);
            }
        } else {
            alertService.showErrorAlert("No selection", "No shop service selected", "Please select shop service in the table.");
        }
    }


    private void handleDeleteShopServiceFail(Result deleteResult) {
        alertService.showErrorAlert("Error", "Invalid operation", deleteResult.getDescription());
    }

    @FXML
    private void handleNewShopService() {
        ShopServiceViewModel shopService = new ShopServiceViewModel();
        boolean okClicked = showShopServiceEditDialog(shopService);

        if (okClicked) {
            Result addResult = shopServiceService.addShopService(shopService);
            if (addResult.isSuccess()) {
                shopServiceData.add(shopService);
            } else {
                alertService.showErrorAlert("Error", "Invalid operation", addResult.getDescription());
            }
        }
    }

    @FXML
    private void handleEditShopService() {
        ShopServiceViewModel shopService = shopServicesTable.getSelectionModel().getSelectedItem();
        if (shopService != null) {
            boolean okClicked = showShopServiceEditDialog(shopService);
            if (okClicked) {
                Result editResult = shopServiceService.editShopService(shopService);
                if (editResult.isSuccess()) {
                    showShopServiceDetails(shopService);
                } else {
                    alertService.showErrorAlert("Error", "Invalid operation", editResult.getDescription());
                }
            }
        } else {
            handleShopServiceNoSelection();
        }
    }

    private void handleShopServiceNoSelection() {
        alertService.showErrorAlert("No Selection", "No shop service selected", "Please select a shop service in the table.");
    }

    private boolean showShopServiceEditDialog(ShopServiceViewModel shopService) {
        Modal modal = viewLoader.load(shopService);
        if (modal == null) return false;

        // Show the dialog and wait until the user closes it
        modal.getModalStage().showAndWait();
        boolean okClicked = modal.getController().isOkClicked();
        return okClicked;
    }
}
