/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.apimanager;

/**
 *
 * @author knsach
 */
public class MovieAPIManager {
    private static final String TMDB_URL = "https://api.themoviedb.org/3/search/movie?";
    private static final String TMDB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDExNjc2YzZmN2IyOGYyOWMyZTk2YjhjNDYxNDQwOCIsInN1YiI6IjY0ZjM3MTMyNWYyYjhkMDExYjRkZmVkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TcSKCjiHCepxIyCzJa0X3gsZITVx4tPLSAiqWIYD49g";


    public static String getMoviesByTitle(String query,int page) {
        String uri = TMDB_URL;
        uri = uri + "query=" + query+"&page="+page;
        String response = "";
        try {
            response = APIClient.get(uri,TMDB_API_KEY);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }
}

