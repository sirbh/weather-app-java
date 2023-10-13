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
public class Book implements Media {
    private String id;
    private String releaseDate;
    private Utility.MediaType mediaType = Utility.MediaType.BOOK;
    private String title;
    private Double rating;

    public Book(String id, String title, String releaseDate, Double rating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public Utility.MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(Utility.MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", releaseDate=" + releaseDate + ", mediaType=" + mediaType + ", title=" + title + ", rating=" + rating + '}';
    }
    
    
}

