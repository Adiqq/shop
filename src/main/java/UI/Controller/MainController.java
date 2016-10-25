package UI.Controller;

import UI.Factories.FXMLLoaderFactory;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage primaryStage;
    private FXMLLoaderFactory loaderFactory;
    @FXML
    private BorderPane rootLayout;

    @Inject
    public MainController(Stage primaryStage, FXMLLoaderFactory loader) {
        this.primaryStage = primaryStage;
        this.loaderFactory = loader;
    }

    public void showShopServiceOverview() {
        try {
            FXMLLoader loader = loaderFactory.getFXMLLoader();
            loader.setLocation(getClass().getResource("/View/ShopServiceOverview.fxml"));
            AnchorPane shopServiceOverview = loader.load();
            rootLayout.setCenter(shopServiceOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showShopServiceOverview();
    }
}
