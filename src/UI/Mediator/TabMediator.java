package UI.Mediator;

import Domain.Infrastructure.ValidationResult;
import Domain.Products.Discount;
import Domain.Products.DiscountPercentage;
import Domain.Products.Product;
import Domain.Products.ProductFeature;
import UI.Controller.Product.*;
import UI.Controller.TabsController;
import UI.Model.DiscountViewModel;
import UI.Model.FeatureViewModel;
import UI.Model.ProductOptionsViewModel;
import UI.Model.ProductViewModel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;

import java.time.LocalDate;

public class TabMediator {
    private static TabsController tabsController;
    private static OverviewController overviewController;

    public static void setTabsController(TabsController tabsController) {
        TabMediator.tabsController = tabsController;
    }

    public static void openDiscounts(ProductViewModel vm) {
        DiscountsController controller = tabsController.createTab(
                vm.getNameProperty().get() + " - Promocje", "/UI/View/Product/ProductDiscounts.fxml");
        controller.setProductViewModel(vm);
    }

    public static void openFeatures(ProductViewModel vm) {
        FeaturesController controller = tabsController.createTab(
                vm.getNameProperty().get() + " - Cechy", "/UI/View/Product/ProductFeatures.fxml");
        controller.setProductViewModel(vm);
    }
    public static void createDiscount(ProductViewModel vm) {
        DiscountCreateEditController controller = tabsController.createTab(
                "Nowa zniżka", "/UI/View/Product/ProductDiscountEdit.fxml");
        controller.setModel(vm,new DiscountViewModel(
                new Discount(new DiscountPercentage(5), LocalDate.now(), LocalDate.now())
                )
        );
    }

    public static void createProductTab(Product product) {
        CreateEditController controller = tabsController.createTab(
                "New product", "/UI/View/Product/ProductEditDialog.fxml");
        controller.setProductOptions(tabsController.getItemsService().getProductOptions());
        controller.setModel(new ProductViewModel(product));
    }

    public static void editProductTab() {
        ProductViewModel model = overviewController.getCurrentSelection();
        if (model != null) {
            CreateEditController controller = tabsController.createTab(
                    model.getNameProperty().get(), "/UI/View/Product/ProductEditDialog.fxml");
            controller.setProductOptions(tabsController.getItemsService().getProductOptions());
            controller.setModel(model);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item not selected");
            alert.setHeaderText("Select an item first.");
            alert.showAndWait();
        }
    }

    public static ProductOptionsViewModel getProductOptionsViewModel() {
        return tabsController.getItemsService().getProductOptions();
    }

    public static void deleteProduct() {
        ProductViewModel model = overviewController.getCurrentSelection();
        if (model != null) {
            tabsController.getItemsService().delete(model);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item not selected");
            alert.setHeaderText("Select an item first.");
            alert.showAndWait();
        }
    }

    public static void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }
    }

    public static void setOverviewController(OverviewController overviewController) {
        TabMediator.overviewController = overviewController;
    }

    public static boolean save(ProductViewModel model) {
        ValidationResult validationResult = model.getProduct().validate();
        if(validationResult.isValid()) {
            tabsController.getItemsService().save(model);
            return true;
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation error");
            StringBuilder builder = new StringBuilder();
            for(String error : validationResult.getErrors()){
                builder.append(error).append(System.lineSeparator());
            }
            alert.setContentText(builder.toString());
            alert.showAndWait();
        }
        return false;
    }

    public static void editDiscount(ProductViewModel product, DiscountViewModel vm) {
        DiscountCreateEditController controller = tabsController.createTab(
                "Zniżka", "/UI/View/Product/ProductDiscountEdit.fxml");
        controller.setModel(product,vm);
    }

    public static void createFeature(ProductViewModel productViewModel) {
        FeatureCreateEditController controller = tabsController.createTab(
                "Nowa cecha", "/UI/View/Product/ProductFeaturesEdit.fxml");
        controller.setModel(productViewModel, new FeatureViewModel(new ProductFeature()));
    }

    public static void editFeature(ProductViewModel productViewModel, FeatureViewModel selectedItem) {
        FeatureCreateEditController controller = tabsController.createTab(
                "Cecha", "/UI/View/Product/ProductFeaturesEdit.fxml");
        controller.setModel(productViewModel, selectedItem);
    }
}
