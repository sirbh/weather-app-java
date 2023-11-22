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
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import tuni.fi.mediafinder.models.Media;


public class GraphView {
    
    private BarChart<String, Number> ratingsChart;
    private BarChart<String, Number> yearsChart;
    private ArrayList<Media> mediaItems = new ArrayList<>();
    private AnchorPane ratingsTab;
    private AnchorPane yearsTab;
    private int startDate;
    private int endDate;
    
    public void initialize (AnchorPane ratingsTab, AnchorPane yearsTab) {
        this.ratingsTab = ratingsTab;
        this.yearsTab = yearsTab;
        ArrayList<BarChart<String, Number>> charts = this.initializeCharts();
        this.ratingsChart = charts.get(0);
        this.yearsChart = charts.get(1);
        this.ratingsTab.getChildren().add(ratingsChart);
        this.yearsTab.getChildren().add(yearsChart);
    }   
    
    public void setDates(int start, int end) {
        this.startDate = start;
        this.endDate = end;
    }
    
    public void setMediaItems(ArrayList<Media> media) {
        this.mediaItems.addAll(media);
        System.out.println("setMediaItems: " + this.mediaItems);
        this.addDataToChart(this.mediaItems, this.ratingsChart, startDate, endDate, false, "ratings");
        this.addDataToChart(this.mediaItems, this.yearsChart, startDate, endDate, true, "years");
    }
    
    private ArrayList<BarChart<String, Number>> initializeCharts() {
        // Ratings chart
        CategoryAxis xAxisRatings = new CategoryAxis(); 
        xAxisRatings.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Books", "Movies")));
        xAxisRatings.setLabel("Media Type");
        NumberAxis yAxisRatings = new NumberAxis(1, 10, 1);
        yAxisRatings.setLabel("Rating");
        BarChart<String, Number> ratingsChart = new BarChart<>(xAxisRatings, yAxisRatings);

        // Years chart
        CategoryAxis xAxisYears = new CategoryAxis(); 
        xAxisYears.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Books", "Movies")));
        xAxisYears.setLabel("Media Type");
        NumberAxis yAxisYears = new NumberAxis();
        yAxisYears.setLabel("Amount");
        BarChart<String, Number> yearsChart = new BarChart<>(xAxisYears, yAxisYears);

        ArrayList<BarChart<String, Number>> charts = new ArrayList<>();
        charts.add(ratingsChart);
        charts.add(yearsChart);

        return charts;
    }


    private void addDataToChart(ArrayList<Media> mediaList, BarChart<String, Number> chart, int startYear, int endYear, boolean useDateRange, String chartType) {
        System.out.println("addDataToChart: " + mediaList);
        ArrayList<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
        
        ArrayList<Double> bookAmounts = new ArrayList<Double>();
        ArrayList<Double> movieAmounts = new ArrayList<Double>(); 
        
        Double rating;
        String name;
        for(Media media : mediaList) {
            rating = media.getRating();
            name = media.getTitle();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(name);
            seriesList.add(series);
            switch(media.getMediaType()) {               
                case BOOK:
                    bookAmounts.add(rating);
                    break;
                case MOVIE:
                    movieAmounts.add(rating);
                    break;
            }
        }
        
        int index = 0;
        for (var ser : seriesList) {
            try {
                ser.getData().add(new XYChart.Data<>("Books", bookAmounts.get(index)));
            } catch (IndexOutOfBoundsException e) {
                ;
            }

            try {
                ser.getData().add(new XYChart.Data<>("Movies", movieAmounts.get(index)));
            } catch (IndexOutOfBoundsException e) {
                ;
            }
            index++;
        }
        
        chart.getData().addAll(seriesList);
    }

}
