package tuni.fi.mediafinder;

import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tuni.fi.mediafinder.models.Media;

public class SingleMediaView {
    public void showSingleMediaItem(Media singleMediaItem, StackPane detailsContainer, Pane searchContainer) {
        Map<String, String> details = singleMediaItem.getVieableMap();
        detailsContainer.getChildren().clear(); // Clear existing children

        String movieTitle = details.getOrDefault("Title", "Title not available");
        String[] genres = details.getOrDefault("Genres", "").split(",");

        VBox vBox = new VBox();
        vBox.setPrefWidth(1280);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30.0);
        // Add movie title
        Label titleLabel = new Label(movieTitle);
        titleLabel.setFont(new Font(24));
        vBox.getChildren().add(titleLabel);

        // Add genres
        HBox genreBox = new HBox(10);
        genreBox.setAlignment(Pos.CENTER);
        for (String genre : genres) {
            Label genreLabel = new Label(genre.trim());
            genreLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 8px; -fx-background-radius: 5px;");
            genreBox.getChildren().add(genreLabel);
        }
        vBox.getChildren().add(genreBox);

        // Add details
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWidth(600);

        int rowIndex = 0;
        for (Map.Entry<String, String> entry : details.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            String labelText = entry.getKey();
            String mapKey = entry.getValue();
            if (labelText.equals("Title") || labelText.equals("Genres")) {
                continue;
            }
            Label keyLabel = new Label(labelText);
            Label valueLabel = new Label(mapKey);
            valueLabel.setWrapText(true);
            valueLabel.setMaxWidth(300);
            keyLabel.setAlignment(Pos.CENTER_LEFT);
            valueLabel.setAlignment(Pos.CENTER_RIGHT);
            grid.add(keyLabel, 0, rowIndex);
            grid.add(valueLabel, 1, rowIndex);
            rowIndex++;
        }
        vBox.getChildren().add(grid);
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            detailsContainer.setVisible(false);
            searchContainer.setVisible(true);
        });
        vBox.getChildren().add(backButton);
        detailsContainer.getChildren().add(vBox);
    }
}
