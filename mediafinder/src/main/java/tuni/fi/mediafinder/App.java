package tuni.fi.mediafinder;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static String STYLESHEET = "styles.css";
    private static String css;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"), 980, 650);
        App.stage = stage;
        css = this.getClass().getResource(STYLESHEET).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("Media Finder");
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
        //System.out.println(MediaUtility.getMediaByReleaseYear("harry potter"));
        //System.out.println(MediaUtility.getMediaByRatings("harry potter"));
        launch();
    }

    public static void toast(String message) {
        Stage pageStage = App.stage;
        Stage toastStage = new Stage();
        toastStage.initOwner(pageStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Label text = new Label(message);

        StackPane toastBox = new StackPane(text);
        toastBox.getStyleClass().add("toast-box");
        Scene scene = new Scene(toastBox);
        scene.getStylesheets().add(css);
        scene.setFill(null);
        toastStage.setScene(scene);

        toastStage.setX(pageStage.getX() + pageStage.getWidth() - 240);
        toastStage.setY(pageStage.getY() + 80);

        toastStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> toastStage.close());
        delay.play();
    }
}