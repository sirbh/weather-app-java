package tuni.fi.mediafinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import tuni.fi.mediafinder.apimanager.http.APIManager;
import tuni.fi.mediafinder.apimanager.mapping.GoogleBooksNamespace;
import tuni.fi.mediafinder.apimanager.mapping.MovieNamespace;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
      // uncomment this line to launch the JavaFX UI
      //  launch();

        String test1 = APIManager.querySearchString("Horror", 1, new GoogleBooksNamespace());
        System.out.println(test1);

        String test2 = APIManager.querySearchString("Horror", 1, new MovieNamespace());
        System.out.println(test2);
     }

}