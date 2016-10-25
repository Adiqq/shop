package UI.Factories;

import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;

/**
 * Created by Adiq on 25.10.2016.
 */
public class FXMLLoaderFactory {
    private Injector injector;

    public FXMLLoaderFactory(Injector injector) {
        this.injector = injector;
    }

    public FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(injector::getInstance);
        return loader;
    }
}
