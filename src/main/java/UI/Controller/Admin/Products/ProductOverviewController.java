package UI.Controller.Admin.Products;

import Service.ShopItemsService;
import UI.Model.ProductViewModel;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductOverviewController implements Initializable {
    @FXML
    private TableView<ProductViewModel> shopServicesTable;

    @FXML
    private TableColumn<ProductViewModel, String> nameColumn;
    @FXML
    private TableColumn<ProductViewModel, String> priceColumn;

    private ShopItemsService shopItemsService;
    private ObservableList<ProductViewModel> shopServiceData = FXCollections.observableArrayList();

    @FXML
    private ProductDetailsController productDetailsController;

    @Inject
    public ProductOverviewController(ShopItemsService shopItemsService) {
        this.shopItemsService = shopItemsService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopServiceData.addAll(shopItemsService.getProducts());
        shopServicesTable.setItems(shopServiceData);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

        productDetailsController.init(shopServicesTable, shopServiceData);

    }


}
