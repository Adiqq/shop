package UI.Controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public abstract class PaneController implements Initializable{
    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
