package tuni.fi.mediafinder.apimanager.http;

import com.google.gson.Gson;
import tuni.fi.mediafinder.apimanager.http.APIClient;
import tuni.fi.mediafinder.apimanager.mapping.*;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.GsonSingleton;
import tuni.fi.mediafinder.utility.Utility;

import java.util.ArrayList;

public class APIManager {

    public static GoogleBooksResponse searchBooks(String searchString, int numberOfBooks) {
        String response = "";
        try {
            response = APIClient.get(GoogleBooksNamespace.bookSearchUrl(searchString, numberOfBooks));
        } catch (Exception error) {
            System.out.println(error);
        }
        return GsonSingleton.getGson().fromJson(response, GoogleBooksResponse.class);
    }

    public static GoogleBooksResponse searchBooks(String searchString) {
        String response = "";
        try {
            response = APIClient.get(GoogleBooksNamespace.bookSearchUrl(searchString));
        } catch (Exception error) {
            System.out.println(error);
        }
        return GsonSingleton.getGson().fromJson(response, GoogleBooksResponse.class);
    }

    public static MovieDBResponse searchMovies(String searchString, int pageNumber) {
        String response = "";
        try {
            response = APIClient.get(MovieNamespace.movieSearchUrl(searchString,pageNumber),MovieNamespace.getAuthKey());
        } catch (Exception error) {
            System.out.println(error);
        }
        return GsonSingleton.getGson().fromJson(response, MovieDBResponse.class);
    }
}


