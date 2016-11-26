package UI.Controller.Admin.Products;

import Infrastructure.Result;
import Service.ShopItemsService;
import UI.Controller.Controller;
import UI.Model.ProductViewModel;
import UI.Model.ShopServiceViewModel;
import UI.Services.AlertService;
import UI.Services.Modal;
import UI.Services.ViewLoader;
import com.google.inject.Inject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ProductDetailsController extends Controller {

    private AlertService alertService;
    private ShopItemsService shopItemsService;
    private ViewLoader viewLoader;
    private TableView<ProductViewModel> shopServicesTable;
    private ObservableList<ProductViewModel> shopServiceData;

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;

    @Inject
    public ProductDetailsController(AlertService alertService, ShopItemsService shopItemsService, ViewLoader viewLoader) {

        this.alertService = alertService;
        this.shopItemsService = shopItemsService;
        this.viewLoader = viewLoader;
    }

    void init(TableView<ProductViewModel> shopServicesTable, ObservableList<ProductViewModel> shopServiceData) {
        this.shopServicesTable = shopServicesTable;
        this.shopServiceData = shopServiceData;
        showShopServiceDetails(null);
        shopServicesTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showShopServiceDetails(newValue))
        );
    }


    private void showShopServiceDetails(ProductViewModel vm) {
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
            ProductViewModel vm = shopServicesTable.getItems().get(selectedIndex);
            Result deleteResult = shopItemsService.deleteShopService(vm);
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
        ProductViewModel shopService = new ProductViewModel();
        boolean okClicked = showShopServiceEditDialog(shopService);

        if (okClicked) {
        }
    }

    @FXML
    private void handleEditShopService() {
        ShopServiceViewModel shopService = shopServicesTable.getSelectionModel().getSelectedItem();
        if (shopService != null) {
            boolean okClicked = showShopServiceEditDialog(shopService);
            if (okClicked) {
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
