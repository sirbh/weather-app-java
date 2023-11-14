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
public class Book implements Media {
    private final String id;
    private final String releaseDate;
    private final Utility.MediaType mediaType = Utility.MediaType.BOOK;
    private final String title;
    private final String description;
    private Double rating;

    public Book(String id, String title, String description, String releaseDate, Double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public Utility.MediaType getMediaType() {
        return mediaType;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public  String getDescription() {
        return this.description != null ? this.description : "";
    }
    @Override
    public void setRating(Double rating) {
        this.rating=rating; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", releaseDate=" + releaseDate + ", mediaType=" + mediaType +
                ", title=" + title + ", description=" + (description != null && description.length() > 20 ? description.substring(0, 20) : description)
                + ", rating=" + rating + '}';
    }

    @Override
    public Map<String, String> getVieableMap(){
        return Map.of("Title", title, "Description", description, "Release date", releaseDate, "Rating", rating.toString());
    }
}

