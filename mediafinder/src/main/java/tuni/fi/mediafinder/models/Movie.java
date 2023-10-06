/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.models;

import tuni.fi.mediafinder.utility.Utility;

/**
 *
 * @author knsach
 */
public class Movie implements Media {
    
    private String id;
    private String releaseDate;
    private Utility.MediaType mediaType = Utility.MediaType.BOOK;
    private String title;
    private Double rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Utility.MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(Utility.MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

