package UI;

import UI.Model.ShopServiceViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView<ShopServiceViewModel> productsTable;

    @FXML
    private TableColumn<ShopServiceViewModel, String> nameColumn;
    @FXML
    private TableColumn<ShopServiceViewModel, String> priceColumn;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());
        ObservableList<ShopServiceViewModel> list = FXCollections.observableArrayList();
        list.add(new ShopServiceViewModel("test", "test"));
        list.add(new ShopServiceViewModel("test2", "test2"));
        productsTable.setItems(list);
    }
}
