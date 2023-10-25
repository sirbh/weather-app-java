package tuni.fi.mediafinder.apimanager.http;

import com.google.gson.Gson;
import tuni.fi.mediafinder.apimanager.http.APIClient;
import tuni.fi.mediafinder.apimanager.mapping.*;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.Utility;

import java.util.ArrayList;

public class APIManager {
    static int PAGES_PER_SEARCH = 4;
    private static String querySearchString(String searchString, int pageNumber, APINamespace namespace) {
        String url = namespace.getBaseUrl() + namespace.getSearchStringQueryKey()
                + "=" + searchString + "&" + namespace.getPageQuery(pageNumber);
        String response = "";
        try {
            response = APIClient.get(url, namespace.getAuthenticationToken());
        } catch (Exception error) {
            System.out.println(error);
        }
        return response;
    }

    public static ArrayList<Media> searchMedia(String searchString, boolean searchBooks, boolean searchMovies) {
        ArrayList<Media> media = new ArrayList<>();
        for(int pageNumber = 1; pageNumber <= PAGES_PER_SEARCH && (searchMovies || searchBooks); pageNumber++) {
            if (searchBooks) {
                ArrayList<Media> books = searchBooks(searchString, 2 * pageNumber);
                books.addAll(searchBooks(searchString, 2 * pageNumber - 1));
                if (books.size() != 2 * GoogleBooksNamespace.BOOKS_PER_PAGE) {
                    searchBooks = false;
                }
                media.addAll(books);
            }
            if (searchMovies) {
                ArrayList<Media> movies = searchMovies(searchString, pageNumber);
                if (movies.size() != MovieNamespace.MOVIES_PER_PAGE) {
                    searchMovies = false;
                }
                media.addAll(movies);
            }
        }
        return media;
    }

    public static ArrayList<Media> searchBooks(String searchString, int pageNumber) {
        return searchMedia(searchString, pageNumber, Utility.MediaType.BOOK);
    }

    public static ArrayList<Media> searchMovies(String searchString, int pageNumber) {
        return searchMedia(searchString, pageNumber, Utility.MediaType.MOVIE);
    }

    private static ArrayList<Media> searchMedia(String searchString, int pageNumber, Utility.MediaType mediaType) {
        APINamespace namespace = mediaType == Utility.MediaType.BOOK ? new GoogleBooksNamespace() : new MovieNamespace();
        String searchResult = querySearchString(searchString, pageNumber, namespace);
        Gson gson = new Gson();
        APIResponse response;
        switch (mediaType) {
            case BOOK:
                response = gson.fromJson(searchResult, GoogleBooksResponse.class);
                break;
            case MOVIE:
                response = gson.fromJson(searchResult, MovieDBResponse.class);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return response.build();
    }

    private static ArrayList<Media> buildGoogleBooks(String searchResult) {
        Gson gson = new Gson();
        GoogleBooksResponse response = gson.fromJson(searchResult, GoogleBooksResponse.class);
        return response.build();
    }

    private static ArrayList<Media> buildMovies(String searchResult) {
        Gson gson = new Gson();
        return new ArrayList<>();
    }
}
