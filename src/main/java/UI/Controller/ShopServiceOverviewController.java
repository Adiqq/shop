package UI.Controller;

import Service.ShopServiceService;
import UI.Factories.FXMLLoaderFactory;
import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopServiceOverviewController implements Initializable {
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

    private Stage primaryStage;
    private FXMLLoaderFactory loaderFactory;
    private ShopServiceService shopServiceService;
    private ObservableList<ShopServiceViewModel> shopServiceData = FXCollections.observableArrayList();

    @Inject
    public ShopServiceOverviewController(Stage primaryStage, FXMLLoaderFactory loaderFactory,
                                         ShopServiceService shopServiceService) {
        this.primaryStage = primaryStage;
        this.loaderFactory = loaderFactory;
        this.shopServiceService = shopServiceService;
    }

    public ObservableList<ShopServiceViewModel> getShopServiceData() {
        return shopServiceData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopServiceData.addAll(shopServiceService.getShopServices());
        shopServicesTable.setItems(shopServiceData);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

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
            shopServicesTable.getItems().remove(selectedIndex);
            shopServiceService.deleteShopService(vm);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle("No selection");
            alert.setHeaderText("No shop service selected");
            alert.setContentText("Please select shop service in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewShopService() {
        ShopServiceViewModel shopService = new ShopServiceViewModel();
        boolean okClicked = showShopServiceEditDialog(shopService);

        if (okClicked) {
            getShopServiceData().add(shopService);
            shopServiceService.addShopService(shopService);
        }
    }

    @FXML
    private void handleEditShopService() {
        ShopServiceViewModel shopService = shopServicesTable.getSelectionModel().getSelectedItem();
        if (shopService != null) {
            boolean okClicked = showShopServiceEditDialog(shopService);
            if (okClicked) {
                showShopServiceDetails(shopService);
                shopServiceService.editShopService(shopService);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No shop service selected");
            alert.setContentText("Please select a shop service in the table.");

            alert.showAndWait();
        }
    }

    public boolean showShopServiceEditDialog(ShopServiceViewModel shopService) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = loaderFactory.getFXMLLoader();
            loader.setLocation(getClass().getResource("/View/ShopServiceEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit shop service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ShopServiceEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setShopService(shopService);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
