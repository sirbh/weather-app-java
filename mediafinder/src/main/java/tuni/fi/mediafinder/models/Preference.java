package tuni.fi.mediafinder.models;


import java.time.LocalDate;
import com.google.gson.annotations.SerializedName;

public class Preference {
    private String searchQuery;
    private boolean movieChecked;
    private boolean bookChecked;
    private String startDate;
    private String endDate;

    // Constructors
    public Preference(String searchQuery, boolean movieChecked, boolean bookChecked, String startDate, String endDate) {
        this.searchQuery = searchQuery;
        this.movieChecked = movieChecked;
        this.bookChecked = bookChecked;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    
    @SerializedName("searchQuery")
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
    @SerializedName("movieChecked")
    public boolean isMovieChecked() {
        return movieChecked;
    }

    public void setMovieChecked(boolean movieChecked) {
        this.movieChecked = movieChecked;
    }
    
    @SerializedName("bookChecked")
    public boolean isBookChecked() {
        return bookChecked;
    }

    public void setBookChecked(boolean bookChecked) {
        this.bookChecked = bookChecked;
    }
    
    @SerializedName("startDate")
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
     
    @SerializedName("endDate")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}