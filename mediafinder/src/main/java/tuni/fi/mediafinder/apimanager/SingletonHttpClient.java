/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.apimanager;

import java.net.http.HttpClient;

/**
 *
 * @author knsach
 */
public class SingletonHttpClient {

    private static HttpClient instance;

    // Private constructor to prevent external instantiation
    private SingletonHttpClient() {
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            // Create a new instance of the HTTP client if it doesn't exist
            instance = HttpClient.newBuilder().build();
        }
        return instance;
    }
}

