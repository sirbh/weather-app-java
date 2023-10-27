package tuni.fi.mediafinder.apimanager.http;

import com.google.gson.Gson;
import tuni.fi.mediafinder.apimanager.http.APIClient;
import tuni.fi.mediafinder.apimanager.mapping.*;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.Utility;

import java.util.ArrayList;

public class APIManager {
    static int MEDIA_PER_PAGE = 160;
    static int NUMBER_OF_MEDIA_TYPES = 2;
    static int ITEMS_PER_MEDIATYPE = MEDIA_PER_PAGE / NUMBER_OF_MEDIA_TYPES;
    static int FIRST_PAGE = 1;
    private static String querySearchString(String searchString, int pageNumber, APINamespace namespace) {
        String url = namespace.getBaseUrl() + namespace.getSearchStringQueryKey()
                + "=" + searchString + "&" + namespace.getPageQuery(pageNumber);
        System.out.println(url);
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
        int mediaFound = 0;
        for(int pageNumber = FIRST_PAGE; pageNumber <= ITEMS_PER_MEDIATYPE / GoogleBooksNamespace.BOOKS_PER_PAGE && searchBooks;
            pageNumber++) {
            ArrayList<Media> books = searchBooks(searchString, FIRST_PAGE);
            mediaFound += books.size();
            media.addAll(books);
        }
        for(int pageNumber = FIRST_PAGE; pageNumber <= ITEMS_PER_MEDIATYPE / MovieNamespace.MOVIES_PER_PAGE
                && searchMovies && mediaFound < MEDIA_PER_PAGE; pageNumber++) {
            ArrayList<Media> movies = searchMovies(searchString, pageNumber);
            if (movies.size() != MovieNamespace.MOVIES_PER_PAGE) {
                searchMovies = false;
            }
            media.addAll(movies);
        }
        return media;
    }

    public static ArrayList<Media> searchBooks(String searchString, int pageNumber) {
        return searchMedia(searchString, pageNumber, Utility.MediaType.BOOK);
    }

    public static ArrayList<Media> searchMovies(String searchString, int pageNumber) {
        return searchMedia(searchString, pageNumber, Utility.MediaType.MOVIE);
    }

    public static String combineSearchWords(String searchString, String spaceEncoding) {
        if (searchString == null || searchString.isEmpty()) {
            return "";
        }

        return String.join(spaceEncoding, searchString.split(" "));
    }

    private static ArrayList<Media> searchMedia(String searchString, int pageNumber, Utility.MediaType mediaType) {
        APINamespace namespace = mediaType == Utility.MediaType.BOOK ? new GoogleBooksNamespace() : new MovieNamespace();
        searchString = combineSearchWords(searchString, namespace.getSpaceEncoding());
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
