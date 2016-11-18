package UI.Services;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Adiq on 18.11.2016.
 */
public class View {
    private Stage primaryStage;
    private AnchorPane pane;

    public View(Stage primaryStage, AnchorPane pane) {
        this.primaryStage = primaryStage;
        this.pane = pane;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnchorPane getPane() {
        return pane;
    }
}
