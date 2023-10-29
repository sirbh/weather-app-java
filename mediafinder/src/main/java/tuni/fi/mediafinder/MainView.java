package tuni.fi.mediafinder;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import tuni.fi.mediafinder.models.Media;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainView {
    // This is the search bar. You can use this to get the keywords for 
    // the API call.
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
    private ArrayList<ArrayList<Pair<Node, Media>>> gridPaneArrayList = null;
    
    // This ArrayList contains the the Media items that are displayed to the 
    // after pressing the search button.
    private ArrayList<Media> mediaItems = null;
    
    private final int gridHeight = 5;
    private final int gridWidth = 2;

    public void initialize() throws IOException {    
        gridPaneArrayList = new ArrayList<>(gridHeight);
        detailsContainer.setVisible(false);
        
        for (int i = 0; i < gridHeight; i++) {
            ArrayList<Pair<Node, Media>> nodeList = new ArrayList<>();
            
            for (int j = 0; j < gridWidth; j++) {
                Label emptyLabel = new Label("");
                searchResultsGrid.add(emptyLabel, j, i);
                nodeList.add(new Pair<> (emptyLabel, null));
            }
            gridPaneArrayList.add(nodeList);
        }
        searchResultsGrid.setVisible(false);
    }
}
