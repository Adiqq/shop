package UI;

import UI.Factories.FXMLLoaderFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Singleton
public class Startup extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new UIModule(primaryStage));
        FXMLLoader loader = injector.getInstance(FXMLLoaderFactory.class).getFXMLLoader();
        loader.setLocation(getClass().getResource("/View/RootLayout.fxml"));

        BorderPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
