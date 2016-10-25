package UI.Controller;

import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage primaryStage;
    @FXML
    private BorderPane rootLayout;

    private ObservableList<ShopServiceViewModel> shopServiceData = FXCollections.observableArrayList();

    @Inject
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ObservableList<ShopServiceViewModel> getShopServiceData() {
        return shopServiceData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showShopServiceOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ShopServiceOverview.fxml"));
            AnchorPane shopServiceOverview = loader.load();
            rootLayout.setCenter(shopServiceOverview);

            ShopServiceOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showShopServiceEditDialog(ShopServiceViewModel shopService) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ShopServiceEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit shop service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ShopServiceEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setShopService(shopService);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.primaryStage.setTitle("Hello World");

        showShopServiceOverview();

        shopServiceData.add(new ShopServiceViewModel("test", "test"));
        shopServiceData.add(new ShopServiceViewModel("test2", "test2"));
    }
}
