package tuni.fi.mediafinder.apimanager.mapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MovieNamespace {

    private static final String TMDB_URL = "https://api.themoviedb.org/3/search/movie?";
    private static final String TMDB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDExNjc2YzZmN2IyOGYyOWMyZTk2YjhjNDYxNDQwOCIsInN1YiI6IjY0ZjM3MTMyNWYyYjhkMDExYjRkZmVkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TcSKCjiHCepxIyCzJa0X3gsZITVx4tPLSAiqWIYD49g";
    private static final String SEARCH_STRING_QUERY_KEY = "query";

    public static final String movieSearchUrl(String query, int pageNumber) {
        String encodedQuery = "";
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error encoding URL parameters: " + e.getMessage());
        }
        return TMDB_URL + SEARCH_STRING_QUERY_KEY + "=" + encodedQuery;
    }

    public static String getAuthKey() {
        return TMDB_API_KEY;
    }
}
