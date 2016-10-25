package UI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javafx.stage.Stage;

public class UIModule extends AbstractModule {
    private Stage stage;

    public UIModule(Stage stage) {
        this.stage = stage;
    }

    protected void configure() {
        //install(new DomainModule());
    }

    @Provides
    public Stage getStage() {
        return stage;
    }
}
