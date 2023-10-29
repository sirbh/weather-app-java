/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tuni.fi.mediafinder.models;

import java.util.Map;

import tuni.fi.mediafinder.utility.Utility;

/**
 *
 * @author knsach
 */
public interface Media {
    String getId();
    String getTitle();
    String getDescription();
    String getReleaseDate();
    Double getRating();
    Utility.MediaType getMediaType();
    Map<String, String> getVieableMap();
}

