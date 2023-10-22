package tuni.fi.mediafinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainViewController {
    
    // This is the search bar. You can use this to get the 
    public TextField searchField;
    public RadioButton moviesRadioButton;
    public RadioButton booksRadioButton;
    public DatePicker startDateCalendar;
    public DatePicker endDateCalendar;
    public Button summaryButton;
    public Button sortButton;
    public GridPane searchResultsGrid;
    public StackPane detailsContainer;
    public Pane searchContainer;
    // This is used for accesing the labels in the gridpane. Also useful for 
    // getting the name of the book or movie when opening 
    // a single media item view.
    private ArrayList<ArrayList<Node>> gridPaneArrayList = null;
    
    /**
     * Initializes the main view and creates an empty grid that will contain
     * the search results.
     * @throws IOException 
     */
    @FXML 
    private void initialize() throws IOException {    
        gridPaneArrayList = new ArrayList<>(5);
        detailsContainer.setVisible(false);
        for (int i = 0; i < 5; i++) {
            ArrayList<Node> nodeList = new ArrayList<>();
            
            for (int j = 0; j < 2; j++) {
                Label emptyLabel = new Label("");
                searchResultsGrid.add(emptyLabel, j, i);
                nodeList.add(emptyLabel);
            }
            gridPaneArrayList.add(nodeList);
        }
        searchResultsGrid.setVisible(false);
    }
    
    /**
     * Handles the clicking of a node in the GridPane.
     * @param event A mouseclick.
     */
    // This function was made with the help of ChatGPT. Leaving this comment
    // so that we'll know where we used the AI.
    @FXML
    private void onGridNodeClicked(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        int clickedColumn = (int) (mouseX / (searchResultsGrid.getWidth() / searchResultsGrid.getColumnConstraints().size()));
        int clickedRow = (int) (mouseY / (searchResultsGrid.getHeight() / searchResultsGrid.getRowConstraints().size()));
        
        // Here comes the code that opens the single media item view.
        Label mediaName = (Label) gridPaneArrayList.get(clickedRow).get(clickedColumn);
        System.out.println(mediaName.getText());
        // testing123
        searchContainer.setVisible(false);
        detailsContainer.setVisible(true);
        Map<String, String> movieDetails = Map.of(
            "Title", "Star Wars: Return of the Jedi",
            "Genres", "Sci-fi,Action,Adventure",
            "Release Date", "25/05/1983",
            "Language", "English",
            "Age Rating", "PG 12",
            "Length", "1h 31min",
            "Description", "Return of the Jedi is the concluding chapter of the original Star Wars trilogy. The Rebel Alliance, having regrouped after their defeat in 'The Empire Strikes Back', plans an attack on the second Death Star, which the Galactic Empire is constructing. Meanwhile, Luke Skywalker seeks to redeem his father, Anakin, and bring him back from the Dark Side.",
            "Director", "Richard Marquand",
            "Producer", "Howard Kazanjian",
            "Screenplay", "Lawrence Kasdan, George Lucas"
        );

        
        updateMovieDetails(movieDetails);
    }
    
    /**
     * A search function that pulls makes the pull request to the APIs.
     * @throws IOException 
     */
    @FXML
    private void search() throws IOException {
        // This function can be used to call the APIs.
        
        // Test data, for illustrating how it would look. 
        // The actual data would be given in a similar format.
        // You can change this if you feel it doesn't work well.
        detailsContainer.setVisible(false);
        ArrayList<String> media1 = new ArrayList(2);
        media1.add(("Star Wars Movie"));
        media1.add(("Star Wars Book"));
        
        ArrayList<ArrayList<String>> media2 = new ArrayList(5);
        media2.add(media1);
        
        for (int i = 0; i < media2.size(); i++) {
            
            for (int j = 0; j < media2.get(i).size(); j++) {
                Label newName = (Label) gridPaneArrayList.get(i).get(j);
                newName.setText(media2.get(i).get(j));
            }
        }
        System.out.println(gridPaneArrayList.get(0).get(0));
        searchResultsGrid.setVisible(true);
    }

    @FXML
    private void updateMovieDetails(Map<String, String> details) {
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

        // Add the "Back" button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            detailsContainer.setVisible(false);
            searchContainer.setVisible(true);
        });
        vBox.getChildren().add(backButton);

        detailsContainer.getChildren().add(vBox);
    }
}
