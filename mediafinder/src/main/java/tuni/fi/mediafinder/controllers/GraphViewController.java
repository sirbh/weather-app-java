package tuni.fi.mediafinder.controllers;
import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import tuni.fi.mediafinder.utility.Utility;

public class GraphViewController {
    public static void plotGraphs(
            Map<Utility.MediaType, Map<String, Long>> releaseYearData, 
            Map<Utility.MediaType, Map<String, Long>> ratingData, 
            AnchorPane ratingsTab, 
            AnchorPane yearsTab
        ) {
            System.out.println(releaseYearData);
            System.out.println(ratingData);
            ratingsTab.getChildren().clear();
            yearsTab.getChildren().clear();

            // Create the BarChart
            BarChart barChartYear = createBarChart(releaseYearData, "Year", "Number of books/movies");
            BarChart barChartRatings = createBarChart(ratingData, "Ratings", "Number of books/movies");

            // Add bar chart to tabs
            yearsTab.getChildren().add(barChartYear);
            ratingsTab.getChildren().add(barChartRatings);

        }

    private static BarChart createBarChart(Map<Utility.MediaType, Map<String, Long>> data, String xAxisLabel, String yAxisLabel) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel("Count");

        // Create the BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between Books and Movies");

        // Movies Series
        XYChart.Series<String, Number> seriesMovies = new XYChart.Series<>();
        seriesMovies.setName("Movies");
        if (data.containsKey(Utility.MediaType.MOVIE)) {
            for (Map.Entry<String, Long> entry : data.get(Utility.MediaType.MOVIE).entrySet()) {
                seriesMovies.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        }

        // Books Series
        XYChart.Series<String, Number> seriesBooks = new XYChart.Series<>();
        seriesBooks.setName("Books");
        if (data.containsKey(Utility.MediaType.BOOK)) {
            for (Map.Entry<String, Long> entry : data.get(Utility.MediaType.BOOK).entrySet()) {
                seriesBooks.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        }

        // Add series to chart
        barChart.getData().addAll(seriesMovies, seriesBooks);

        return barChart;
    }
}
