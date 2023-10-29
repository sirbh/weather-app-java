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
import javafx.util.Pair;
import tuni.fi.mediafinder.apimanager.http.APIManager;
import tuni.fi.mediafinder.models.Media;

public class Controller {
    // This is the search bar. You can use this to get the keywords for 
    // the API call.
    private TextField searchField;
    private RadioButton moviesRadioButton;
    private RadioButton booksRadioButton;
    private DatePicker startDateCalendar;
    private DatePicker endDateCalendar;
    private Button summaryButton;
    private Button sortButton;
    private GridPane searchResultsGrid;
    private StackPane detailsContainer;
    private Pane searchContainer;
    
    // This is used for accesing the labels in the gridpane. Also useful for 
    // getting the name of the book or movie when opening 
    // a single media item view.
    private ArrayList<ArrayList<Pair<Node, Media>>> gridPaneArrayList = null;
    
    // This ArrayList contains the the Media items that are displayed to the 
    // after pressing the search button.
    private ArrayList<Media> mediaItems = null;
    
    private final int gridHeight = 5;
    private final int gridWidth = 2;

    // Initialize MainView and SingleMediaView here.
    private MainView mainView = new MainView();
    private SingleMediaView singleMediaView = new SingleMediaView(detailsContainer);
    
    /**
     * Initializes the main view and creates an empty grid that will contain
     * the search results.
     * @throws IOException 
     */
    @FXML 
    private void initialize() throws IOException {
        mainView.initialize();
    }
    
    /**
     * Handles the clicking of a node in the GridPane. Passes the event to singelMediaView.
     * @param event A mouseclick.
     */
    @FXML
    private void onGridNodeClicked(MouseEvent event) {
        // TODO: There's a better way to get the right node.
        double mouseX = event.getX();
        double mouseY = event.getY();
        int clickedColumn = (int) (mouseX / (searchResultsGrid.getWidth() / searchResultsGrid.getColumnConstraints().size()));
        int clickedRow = (int) (mouseY / (searchResultsGrid.getHeight() / searchResultsGrid.getRowConstraints().size()));
        
        // Here comes the code that opens the single media item view.
        Media singleMediaItem = gridPaneArrayList.get(clickedRow).get(clickedColumn).getValue();

        if (singleMediaItem != null) {
            searchContainer.setVisible(false); // Hide Search container
            detailsContainer.setVisible(true); // Show Single media container
            singleMediaView.onGridNodeClicked(singleMediaItem);
        }
    }

    @FXML
    private void onBackButtonClicked() {
        detailsContainer.setVisible(false);
        searchContainer.setVisible(true);
    }
    
    /**
     * A search function that makes the pull request to the APIs.
     * @throws IOException 
     */
    @FXML
    private void search() throws IOException {
        // This function can be used to call the APIs.
        if (searchField.getText().length() != 0) {
            
            detailsContainer.setVisible(false);

            mediaItems = APIManager.searchMedia(searchField.getText(),
                    booksRadioButton.isSelected(), moviesRadioButton.isSelected());
            
            if (mediaItems.size() < gridHeight * gridWidth) {
                
            }
            
            int index = 0;
            for (int i = 0; i < gridHeight; i++) {
               for (int j = 0; j < gridWidth; j++) {
                    if (index < mediaItems.size()) {
                        Label newName = (Label) gridPaneArrayList.get(i).get(j).getKey();
                        newName.setText(mediaItems.get(index).getTitle());
                        Pair<Node, Media> newPair = 
                                new Pair<> (gridPaneArrayList.get(i).get(j).getKey(), 
                                        mediaItems.get(index));
                        gridPaneArrayList.get(i).set(j, newPair);
                        index++;
                    }
                    else {
                        break;
                   }
               } 
            }
            searchResultsGrid.setVisible(true);
        }
    }
    
    /**
     * Checks if the radio button for movies is enabled when trying to disable 
     * the radio button for books. If it is enabled, then the function does nothing.
     * Otherwise, it will keep enabling the books radio button.
     * This prevents a situation where the user has disabled both radio buttons 
     * and tries to search for media.
     */
    @FXML 
    private void checkIfMoviesRadioButtonEnabled() {
        
        if (!moviesRadioButton.isSelected()) {
            booksRadioButton.setSelected(true);
        }
    }
    
    /**
     * Checks if the radio button for books is enabled when trying to disable 
     * the radio button for movies. If it is enabled, then the function does nothing.
     * Otherwise, it will keep enabling the movies radio button.
     * This prevents a situation where the user has disabled both radio buttons 
     * and tries to search for media.
     */
    @FXML
    private void checkIfBooksRadioButtonEnabled() {
        
        if (!booksRadioButton.isSelected()) {
            moviesRadioButton.setSelected(true);
        }
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