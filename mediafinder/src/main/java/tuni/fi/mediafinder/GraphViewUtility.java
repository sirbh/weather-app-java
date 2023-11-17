package tuni.fi.mediafinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.Utility.MediaType;
import static tuni.fi.mediafinder.utility.Utility.MediaType.BOOK;
import static tuni.fi.mediafinder.utility.Utility.MediaType.MOVIE;

public class GraphViewUtility {

    public ArrayList<BarChart<String, Number>> initializeCharts() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Books", "Movies")));
        xAxis.setLabel("Media Type");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");

        BarChart<String, Number> ratingsChart = new BarChart<>(xAxis, yAxis);
        BarChart<String, Number> yearsChart = new BarChart<>(xAxis, yAxis);

        ArrayList<BarChart<String, Number>> charts = new ArrayList<>();
        charts.add(ratingsChart);
        charts.add(yearsChart);

        return charts;
    }

    public void addDataToChart(ArrayList<Media> mediaList, BarChart<String, Number> chart, int startYear, int endYear, boolean useDateRange, String chartType) {
        
        ArrayList<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
        
        for(int i = 0; i <= 10; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(Integer.toString(i));
            seriesList.add(series);
        }
        
        ArrayList<Integer> bookAmounts = Stream.generate(() -> 0)
                .limit(11).collect(Collectors.toCollection(ArrayList::new));
        
        ArrayList<Integer> movieAmounts = Stream.generate(() -> 0)
                .limit(11).collect(Collectors.toCollection(ArrayList::new));
        int rating;
        
        for(Media media : mediaList) {
            rating = (int) Math.round(media.getRating());
            
            switch(media.getMediaType()) {               
                case BOOK:
                    bookAmounts.set(rating, bookAmounts.get(rating) + 1);
                    break;
                case MOVIE:
                    movieAmounts.set(rating, movieAmounts.get(rating) + 1);
                    break;
            }
        }
        
        int index = 0;
        for (var ser : seriesList) {
            ser.getData().add(new XYChart.Data<>("Books", bookAmounts.get(index)));
            ser.getData().add(new XYChart.Data<>("Movies", movieAmounts.get(index)));
        }
        
        chart.getData().addAll(seriesList);
    }
}
