package tuni.fi.mediafinder;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Pair;
import tuni.fi.mediafinder.models.Media;
import javafx.scene.layout.GridPane;

public class MainView {
    // This is the search bar. You can use this to get the keywords for 
    // the API call.
    public GridPane searchResultsGrid;
    public int gridHeight;
    public int gridWidth;
    public ArrayList<ArrayList<Pair<Node, Media>>> gridPaneArrayList;

    public MainView(int gridHeight, int gridWidth) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    public void initialize(ArrayList<ArrayList<Pair<Node, Media>>> gridPaneArrayList, GridPane searchResultsGrid) throws IOException {
        this.gridPaneArrayList = gridPaneArrayList;
        this.searchResultsGrid = searchResultsGrid;
        
        for (int i = 0; i < gridHeight; i++) {
            ArrayList<Pair<Node, Media>> nodeList = new ArrayList<>();
            
            for (int j = 0; j < gridWidth; j++) {
                Label emptyLabel = new Label("");
                searchResultsGrid.add(emptyLabel, j, i);
                nodeList.add(new Pair<> (emptyLabel, null));
            }
            this.gridPaneArrayList.add(nodeList);
        }
        searchResultsGrid.setVisible(false);
    }

    public void showSearchResults(ArrayList<Media> mediaItems) throws IOException {
        if (mediaItems.size() < gridHeight * gridWidth) {
            // TODO: Something here?
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
