package UI.Controller.Product;

import Domain.Products.Category;
import Domain.Products.Picture;
import Domain.Products.ProductType;
import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.ProductOptionsViewModel;
import UI.Model.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

/**
 * Controller responsible for creating and editing product tab
 */
public class CreateEditController extends TabController {
    private ProductViewModel model;
    private ProductOptionsViewModel productOptions;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox categoryChoice;
    @FXML
    private ChoiceBox productTypeChoice;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Sets model for tab
     * @param model
     */
    public void setModel(ProductViewModel model) {
        this.model = model;
        nameField.textProperty().bindBidirectional(model.getNameProperty());
        priceField.textProperty().bindBidirectional(model.getPriceProperty());
        categoryChoice.setValue(model.getCategoryProperty().getValue());
        productTypeChoice.setItems
                (FXCollections.observableArrayList(model.getCategoryProperty().getValue().getProductTypes()));
        productTypeChoice.setValue(model.getProductTypeProperty().getValue());
        productTypeChoice.valueProperty()
                .addListener((observable, oldValue, newValue) ->
                        model.getProductTypeProperty().setValue((ProductType)newValue));

        setImage();
    }
    public void setImage(){
        Image image = model.getImage();
        if(image != null) {
            imageView.setImage(image);
            imageView.setVisible(true);
        } else{
            imageView.setVisible(false);
        }
    }
    public ProductViewModel getModel() {
        return model;
    }

    public boolean save(){
        return TabMediator.save(getModel());
    }
    public void saveAndClose(){
        if(save()) {
            TabMediator.closeTab(this.getTab());
        }
    }


    public ProductOptionsViewModel getProductOptions() {
        return productOptions;
    }

    /**
     * Sets avaible product options for given product
     * @param productOptions
     */
    public void setProductOptions(ProductOptionsViewModel productOptions) {
        this.productOptions = productOptions;
        categoryChoice.setItems(productOptions.getCategories());
    }

    /**
     * Triggers image picker window
     * @param actionEvent
     */
    public void pickImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            Picture pic = loadImage(file);
            if (pic != null) {
                model.getProduct().setPicture(pic);
                setImage();
            }
        }

    }

    /**
     * Load image from file
     * @param file
     * @return
     */
    public Picture loadImage(File file){
        try {
            Picture pic = new Picture();
            pic.setContent(Files.readAllBytes(file.toPath()));
            pic.setPictureType(getExtension(file.getPath()));
            return pic;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts file extension from file
     * @param path
     * @return
     */
    private String getExtension(String path) {
        int dot = path.lastIndexOf('.');
        return path.substring(dot + 1);
    }

    public void cleanImage(ActionEvent actionEvent) {
        model.getProduct().setPicture(null);
        setImage();
    }

    /**
     * Sends request to open product features tab
     * @param actionEvent
     */
    public void openProductFeatures(ActionEvent actionEvent) {
        TabMediator.openFeatures(getModel());
    }

    /**
     * Sends request to open product discounts
     * @param actionEvent
     */
    public void openProductDiscounts(ActionEvent actionEvent) {
        TabMediator.openDiscounts(getModel());
    }
}
