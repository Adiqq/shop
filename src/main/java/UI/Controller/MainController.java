package UI.Controller;

import UI.Factories.FXMLLoaderFactory;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private FXMLLoaderFactory loaderFactory;
    @FXML
    private BorderPane rootLayout;

    @Inject
    public MainController(FXMLLoaderFactory loader) {
        this.loaderFactory = loader;
    }

    public void showAdminPerspective() {
        show("/View/Admin/AdminTabs.fxml");
    }

    public void showUserPerspective() {
        show("/View/User/User.fxml");
    }

    private void show(String view) {
        try {
            FXMLLoader loader = loaderFactory.getFXMLLoader();
            loader.setLocation(getClass().getResource(view));
            AnchorPane pane = loader.load();
            rootLayout.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAdminPerspective();
    }
}
