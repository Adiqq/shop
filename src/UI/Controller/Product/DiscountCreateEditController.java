package UI.Controller.Product;

import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.DiscountViewModel;
import UI.Model.ProductViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class DiscountCreateEditController  extends TabController {
    @FXML
    public Slider percentageSlider;
    @FXML
    public DatePicker beginDatePicker;
    @FXML
    public DatePicker endDatePicker;

    ProductViewModel vm;
    private DiscountViewModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setModel(ProductViewModel vm, DiscountViewModel model) {
        this.vm = vm;
        this.model = model;
        if(model.getPercentage() != null) {
            percentageSlider.valueProperty().setValue(model.getPercentage().getValue());
            percentageSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                    model.getPercentage().setValue(newValue.intValue()));
        }
        beginDatePicker.valueProperty().bindBidirectional(model.beginDateProperty());
        endDatePicker.valueProperty().bindBidirectional(model.endDateProperty());
    }

    public void saveAndClose(ActionEvent actionEvent) {
        if(!vm.getDiscounts().contains(this.model)){
            vm.getDiscounts().add(model);
        }
        TabMediator.closeTab(getTab());
    }
}
