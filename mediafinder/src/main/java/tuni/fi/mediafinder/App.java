package tuni.fi.mediafinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import tuni.fi.mediafinder.apimanager.GoogleBooksAPIManager;
import tuni.fi.mediafinder.apimanager.MovieAPIManager;

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
      // test call to GoogleBookAPI
      
      String resp =   GoogleBooksAPIManager.getBooksByTitle("Horror", 10, 10);
      System.out.println(resp);
      // test call tp TMDB movies API
      
      String resp2 =   MovieAPIManager.getMoviesByTitle("Horror", 1);
      System.out.println(resp2);
     }

}