package UI.Controller.Admin;

import Service.ShopServiceService;
import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopServiceOverviewController implements Initializable {
    @FXML
    private TableView<ShopServiceViewModel> shopServicesTable;

    @FXML
    private TableColumn<ShopServiceViewModel, String> nameColumn;
    @FXML
    private TableColumn<ShopServiceViewModel, String> priceColumn;

    private ShopServiceService shopServiceService;
    private ObservableList<ShopServiceViewModel> shopServiceData = FXCollections.observableArrayList();

    @FXML
    private ShopServiceDetailsController shopServiceDetailsController;

    @Inject
    public ShopServiceOverviewController(ShopServiceService shopServiceService) {
        this.shopServiceService = shopServiceService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopServiceData.addAll(shopServiceService.getShopServices());
        shopServicesTable.setItems(shopServiceData);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

        shopServiceDetailsController.init(shopServicesTable, shopServiceData);

    }


}
