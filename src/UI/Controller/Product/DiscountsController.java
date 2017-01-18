package UI.Controller.Product;

import Domain.Common.Percentage;
import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.DiscountViewModel;
import UI.Model.ProductViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller responsible for displaying discounts for selected product
 */
public class DiscountsController extends TabController {
    private ProductViewModel productViewModel;
    @FXML
    private TableView<DiscountViewModel> discountsTable;
    @FXML
    private TableColumn<DiscountViewModel, LocalDate> beginDate;
    @FXML
    private TableColumn<DiscountViewModel, LocalDate> endDate;
    @FXML
    private TableColumn<DiscountViewModel, Percentage> discountPercentage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setProductViewModel(ProductViewModel vm) {
        productViewModel = vm;
        discountsTable.setItems(vm.getDiscounts());

        beginDate.setCellValueFactory(cellData -> cellData.getValue().beginDateProperty());
        endDate.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
        discountPercentage.setCellValueFactory(cellData -> cellData.getValue().percentageProperty());
    }

    public void add(ActionEvent actionEvent) {
        TabMediator.createDiscount(productViewModel);
    }

    public void edit(ActionEvent actionEvent) {
        DiscountViewModel selectedItem = discountsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TabMediator.editDiscount(productViewModel, selectedItem);
        }
    }

    public void delete(ActionEvent actionEvent) {
        DiscountViewModel selectedItem = discountsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            productViewModel.getDiscounts().remove(selectedItem);
        }
    }
}
