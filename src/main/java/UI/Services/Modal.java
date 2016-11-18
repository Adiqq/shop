package UI.Services;

import UI.Controller.ModalController;
import javafx.stage.Stage;

/**
 * Created by Adiq on 18.11.2016.
 */
public class Modal {
    private View view;
    private Stage modalStage;
    private ModalController controller;

    public Modal(View view, Stage stage, ModalController controller) {
        this.view = view;
        this.modalStage = stage;
        this.controller = controller;
    }

    public View getView() {
        return view;
    }

    public Stage getModalStage() {
        return modalStage;
    }

    public ModalController getController() {
        return controller;
    }
}
