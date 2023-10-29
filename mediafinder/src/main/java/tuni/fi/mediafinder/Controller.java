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

    public final int gridHeight = 5;
    public final int gridWidth = 2;

    // This is used for accesing the labels in the gridpane. Also useful for 
    // getting the name of the book or movie when opening 
    // a single media item view.
    public ArrayList<ArrayList<Pair<Node, Media>>> gridPaneArrayList = new ArrayList<>(gridHeight);

    // Initialize MainView and SingleMediaView.
    public MainView mainView = new MainView(gridHeight, gridWidth);
    public SingleMediaView singleMediaView = new SingleMediaView();
    
    /**
     * Initializes the main view and creates an empty grid that will contain
     * the search results.
     * @throws IOException 
     */
    @FXML 
    public void initialize() throws IOException {
        detailsContainer.setVisible(false);
        mainView.initialize(gridPaneArrayList, searchResultsGrid);
    }
    
    @FXML
    private void search() throws IOException {
        detailsContainer.setVisible(false);
        ArrayList<Media> mediaItems = APIManager.searchMedia(searchField.getText(),
                booksRadioButton.isSelected(), moviesRadioButton.isSelected());
        System.out.println(mediaItems);
        mainView.showSearchResults(mediaItems);
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

    /**
     * Handles the clicking of a node in the GridPane. Passes the event to singelMediaView.
     * @param event A mouseclick.
     */
    @FXML
    private void onGridNodeClicked(MouseEvent event) {
        // TODO: There has to be a better way to get the right node.
        double mouseX = event.getX();
        double mouseY = event.getY();
        System.out.println(event.getTarget());
        int clickedColumn = (int) (mouseX / (searchResultsGrid.getWidth() / searchResultsGrid.getColumnConstraints().size()));
        int clickedRow = (int) (mouseY / (searchResultsGrid.getHeight() / searchResultsGrid.getRowConstraints().size()));
        
        // Here comes the code that opens the single media item view.
        Media singleMediaItem = gridPaneArrayList.get(clickedRow).get(clickedColumn).getValue();

        if (singleMediaItem != null) {
            searchContainer.setVisible(false); // Hide Search container
            detailsContainer.setVisible(true); // Show Single media container
            singleMediaView.showSingleMediaItem(singleMediaItem, detailsContainer);
        }
    }

    @FXML
    private void onBackButtonClicked() {
        detailsContainer.setVisible(false);
        searchContainer.setVisible(true);
    }
}
