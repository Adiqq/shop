package UI.Controller;

import UI.Services.ShopItemService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    BorderPane rootLayout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/UI/View/tabs.fxml")
            );
            Pane pane = loader.load();
            TabsController controller =
                    loader.getController();
            controller.setItemsService(new ShopItemService());
            rootLayout.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
