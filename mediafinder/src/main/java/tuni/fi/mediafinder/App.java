package tuni.fi.mediafinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import tuni.fi.mediafinder.apimanager.http.APIManager;
import tuni.fi.mediafinder.apimanager.mapping.GoogleBooksNamespace;
import tuni.fi.mediafinder.apimanager.mapping.GoogleBooksResponse;
import tuni.fi.mediafinder.apimanager.mapping.MovieNamespace;
import tuni.fi.mediafinder.models.Media;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"), 1280, 720);
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
        launch();

        /*ArrayList<Media> media = APIManager.searchMedia("Horro man", true, true);

        System.out.println(media.size());*/
     }

}