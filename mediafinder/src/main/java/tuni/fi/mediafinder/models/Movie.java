/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.models;

import java.util.Map;

import tuni.fi.mediafinder.utility.Utility;

/**
 *
 * @author knsach
 */
public class Movie implements Media {
    private final String id;
    private final String releaseDate;
    private final Utility.MediaType mediaType = Utility.MediaType.MOVIE;
    private final String title;
    private final String description;
    private Double rating;

    public Movie(String id, String title, String description, String releaseDate, Double rating) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Utility.MediaType getMediaType() {
        return mediaType;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() { return this.description != null ? this.description : ""; }

    public Double getRating() {
        return rating;
    }
    @Override
    public void setRating(Double rating) {
        this.rating=rating; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", releaseDate=" + releaseDate + ", mediaType=" + mediaType +
                ", title=" + title + ", description=" +  (description != null && description.length() > 20 ? description.substring(0, 20) : description)
                + ", rating=" + rating + '}';
    }

    @Override
    public Map<String, String> getVieableMap(){
        return Map.of("Title", title, "Description", description, "Release date", releaseDate, "Rating", rating.toString());
    }
}

