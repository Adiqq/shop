package UI.Controller.Product;

import Domain.Factories.ProductFactory;
import Domain.Products.Category;
import Domain.Products.Product;
import Domain.Services.PriceType;
import Domain.Services.ShopService;
import UI.Controller.TabController;
import UI.Mediator.TabMediator;
import UI.Model.DiscountViewModel;
import UI.Model.FeatureViewModel;
import UI.Model.ProductOptionsViewModel;
import UI.Model.ProductViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailsController extends TabController {
    @FXML
    public Label nameLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public ImageView imageView;
    @FXML
    public TextFlow textFlow;
    private ProductViewModel model;

    public void create(){
        showCategoryDialog().ifPresent(x -> TabMediator.createProductTab(ProductFactory.createProduct(x)));
    }
    public void edit(){ TabMediator.editProductTab();}
    public void delete() {TabMediator.deleteProduct();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public ProductViewModel getModel() {
        return model;
    }

    public void SetSelectionListener(ReadOnlyObjectProperty<ProductViewModel> selectionProperty){
        selectionProperty.addListener(
                ((observable, oldValue, newValue) -> {
                    model = newValue;
                    if(model != null) {
                        nameLabel.setText(model.getNameProperty().getValue());
                        priceLabel.setText(model.getPriceProperty().getValue());
                        imageView.setImage(model.getImage());
                        textFlow.getChildren().clear();
                        Product product = model.getProduct();
                        if(product != null && product.getServices() != null) {
                            for (ShopService service : product.getServices()){
                                StringBuilder builder = new StringBuilder();
                                builder.append(service.getName());
                                builder.append(" : ");
                                builder.append(service.getPrice());
                                if(service.getPriceType() == PriceType.PerHour){
                                    builder.append(" zł/godzinę");
                                } else{
                                    builder.append(" zł");
                                }
                                builder.append(System.lineSeparator());
                                textFlow.getChildren().add(new Text(builder.toString()));
                            }
                        }
                        for(FeatureViewModel feature : model.getFeatures()){
                            StringBuilder builder = new StringBuilder();
                            builder.append(feature.getNameProperty());
                            builder.append(" : ");
                            builder.append(feature.getValueProperty());
                            builder.append(System.lineSeparator());
                            textFlow.getChildren().add(new Text(builder.toString()));
                        }
                        for(DiscountViewModel discount : model.getDiscounts()){
                            StringBuilder builder = new StringBuilder();
                            builder.append(discount.getBeginDate());
                            builder.append(" - ");
                            builder.append(discount.getEndDate());
                            builder.append(" - ");
                            builder.append(discount.getPercentage());
                            builder.append(" zniżki");
                            builder.append(System.lineSeparator());
                            textFlow.getChildren().add(new Text(builder.toString()));
                        }
                    }
                })
        );
    }

    private Optional<Category> showCategoryDialog(){
        ProductOptionsViewModel productOptionsViewModel = TabMediator.getProductOptionsViewModel();

        ChoiceDialog<Category> dialog = new ChoiceDialog<>(Category.Gitara, productOptionsViewModel.getCategories());
        dialog.setTitle("Select category");
        dialog.setHeaderText("Pick your instrument:");
        return dialog.showAndWait();
    }
}
