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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
}
