package UI.Controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * Abstract controller with access to view's pane
 */
public abstract class PaneController implements Initializable{
    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
