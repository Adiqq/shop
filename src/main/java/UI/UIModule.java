package UI;

import Service.ServiceModule;
import UI.Factories.FXMLLoaderFactory;
import UI.Services.AlertService;
import UI.Services.ViewLoader;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import javafx.stage.Stage;

class UIModule extends AbstractModule {
    private Stage stage;

    UIModule(Stage stage) {
        this.stage = stage;
    }

    protected void configure() {
        install(new ServiceModule());
        bind(ViewLoader.class);
        bind(AlertService.class);
    }

    @Provides
    public Stage getStage() {
        return stage;
    }

    @Provides
    public FXMLLoaderFactory getFXMLLoaderFactory(Injector injector) {
        return new FXMLLoaderFactory(injector);
    }
}
