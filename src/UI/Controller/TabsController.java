package UI.Controller;

import UI.Controller.Product.OverviewController;
import UI.Mediator.TabMediator;
import UI.Model.ProductViewModel;
import UI.Services.ShopItemService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabsController implements Initializable {
    @FXML
    private TabPane tabPane;
    private ShopItemService itemsService;
    public <T extends TabController> T createTab (String tabText, String viewPath){
        return createTab(tabText,viewPath, true);
    }
    public <T extends TabController> T createTab (String tabText, String viewPath, boolean isClosable){
            Tab tab = new Tab();
            tab.setText(tabText);
            T controller = createPane(viewPath);
            tab.setContent(controller.getPane());
            tab.setClosable(isClosable);
            controller.setTab(tab);
            tabPane.getTabs().add(tab);
            return controller;
    }
    private <T extends TabController>T createPane(String viewPath){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(viewPath)
            );
            Pane pane = loader.load();

            T controller =
                    loader.getController();
            controller.setPane(pane);
            return controller;
        }
        catch (IOException e){

        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public ShopItemService getItemsService() {
        return itemsService;
    }

    public void setItemsService(ShopItemService itemsService) {
        this.itemsService = itemsService;
        TabMediator.setTabsController(this);
        OverviewController overview = this.createTab(
                "Products",
                "/UI/View/Product/ProductOverview.fxml",
                false);
        TabMediator.setOverviewController(overview);
        ObservableList<ProductViewModel> allProducts = getItemsService().getAllProducts();
        overview.setShopServiceData(allProducts);
    }
}
