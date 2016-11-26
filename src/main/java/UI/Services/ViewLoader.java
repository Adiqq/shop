package UI.Services;

import UI.Controller.Admin.ShopServices.ShopServiceEditDialogController;
import UI.Factories.FXMLLoaderFactory;
import UI.Model.ShopServiceViewModel;
import com.google.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Adiq on 18.11.2016.
 */
public class ViewLoader {
    private FXMLLoaderFactory loaderFactory;
    private Stage primaryStage;

    @Inject
    public ViewLoader(FXMLLoaderFactory factory, Stage primaryStage) {
        this.loaderFactory = factory;
        this.primaryStage = primaryStage;
    }

    public Modal load(ShopServiceViewModel service) {
        FXMLLoader loader = loaderFactory.getFXMLLoader();
        String viewPath = "/View/Admin/ShopServices/ShopServiceEditDialog.fxml";
        View view = loadView(viewPath, loader);
        Stage stage = getModalStage(view, "Edit shop service");
        ShopServiceEditDialogController controller = setController(stage, service, loader);
        return new Modal(view, stage, controller);
    }

    private View loadView(String view, FXMLLoader loader) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            loader.setLocation(getClass().getResource(view));
            AnchorPane pane = loader.load();
            return new View(primaryStage, pane);
        } catch (Exception e) {
            return null;
        }
    }

    private Stage getStage(View view, String title, Modality modality) {
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(modality);
        dialogStage.initOwner(view.getPrimaryStage());
        Scene scene = new Scene(view.getPane());
        dialogStage.setScene(scene);
        return dialogStage;
    }

    private Stage getModalStage(View view, String title) {
        // Create the dialog Stage.
        return getStage(view, title, Modality.WINDOW_MODAL);
    }

    private ShopServiceEditDialogController setController(Stage dialogStage, ShopServiceViewModel service, FXMLLoader loader) {
        ShopServiceEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setShopService(service);
        return controller;
    }
}
