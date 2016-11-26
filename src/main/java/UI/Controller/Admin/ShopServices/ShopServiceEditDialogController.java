package UI.Controller.Admin.ShopServices;

import UI.Controller.ModalController;
import UI.Model.ShopServiceViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShopServiceEditDialogController extends ModalController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    private Stage dialogStage;

    private ShopServiceViewModel shopService;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setShopService(ShopServiceViewModel shopService) {
        this.shopService = shopService;

        nameField.setText(shopService.getName());
        priceField.setText(shopService.getPrice());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            shopService.setName(nameField.getText());
            shopService.setPrice(priceField.getText());

            super.okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);

        alert.showAndWait();

        return false;
    }

}
