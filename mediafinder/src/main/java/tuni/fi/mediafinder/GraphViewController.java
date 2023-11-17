package tuni.fi.mediafinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;
import tuni.fi.mediafinder.GraphViewUtility;
import tuni.fi.mediafinder.Controller;
import tuni.fi.mediafinder.models.Media;

/**
 * FXML Controller class
 */
public class GraphViewController {
    
    private BarChart<String, Number> ratingsChart;
    private BarChart<String, Number> yearsChart;
    private GraphViewUtility graphUtility = new GraphViewUtility();
    static private int startDate;
    static private int endDate;
    static private ArrayList<Media> mediaItems = new ArrayList<>();
    
    @FXML
    private AnchorPane ratingsTab;
    @FXML
    private AnchorPane yearsTab;
    
    /**
     * Initializes the controller class.
     * @throws java.io.IOException
     */
    @FXML
    public void initialize() throws IOException { 
        ArrayList<BarChart<String, Number>> charts = this.graphUtility.initializeCharts();
        this.ratingsChart = charts.get(0);
        this.yearsChart = charts.get(1);
        this.graphUtility.addDataToChart(GraphViewController.mediaItems, this.ratingsChart, startDate, endDate, false, "ratings");
        this.graphUtility.addDataToChart(GraphViewController.mediaItems, this.yearsChart, startDate, endDate, true, "years");
        ratingsTab.getChildren().add(ratingsChart);
        yearsTab.getChildren().add(yearsChart);
        
    }   
    
    static public void setDates(int start, int end) {
        GraphViewController.startDate = start;
        GraphViewController.endDate = end;
    }
    
    static public void setMediaItems(ArrayList<Media> media) {
        GraphViewController.mediaItems.addAll(media);
    }
    
    @FXML
    private void switchToMainView() throws IOException {
        App.setRoot("mainView");
    }
    
}
