package UI.Services;

import com.google.inject.Inject;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by Adiq on 18.11.2016.
 */
public class AlertService {
    private Stage primaryStage;

    @Inject
    public AlertService(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
