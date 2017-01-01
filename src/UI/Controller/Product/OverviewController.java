package UI.Controller.Product;

import UI.Controller.TabController;
import UI.Controller.TabsController;
import UI.Model.ProductViewModel;
import UI.Services.ShopItemService;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController extends TabController {
    @FXML
    public TextField filterText;
    private ObservableList<ProductViewModel> shopServiceData;
    @FXML
    private TableView<ProductViewModel> productTable;
    @FXML
    private TableColumn<ProductViewModel, String> nameColumn;
    @FXML
    private TableColumn<ProductViewModel, String> priceColumn;
    @FXML
    private SplitPane splitPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/UI/View/Product/ProductDetails.fxml")
            );
            Pane pane = loader.load();
            DetailsController controller =
                    loader.getController();
            controller.SetSelectionListener(getSelectionProperty());
            splitPane.getItems().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<ProductViewModel> getShopServiceData() {
        return shopServiceData;
    }

    public void setShopServiceData(ObservableList<ProductViewModel> shopServiceData) {
        this.shopServiceData = shopServiceData;
        productTable.setItems(this.shopServiceData);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        FilteredList<ProductViewModel> filteredData = new FilteredList<>(shopServiceData, p -> true);

        filterText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNameProperty().get().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        productTable.setItems(filteredData);
    }

    public ProductViewModel getCurrentSelection(){
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            return productTable.getItems().get(selectedIndex);
        }
        return null;
    }

    public ReadOnlyObjectProperty<ProductViewModel> getSelectionProperty(){
        return productTable.getSelectionModel().selectedItemProperty();
    }
}
