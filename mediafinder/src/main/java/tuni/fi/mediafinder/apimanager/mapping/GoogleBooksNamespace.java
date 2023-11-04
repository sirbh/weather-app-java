package tuni.fi.mediafinder.apimanager.mapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleBooksNamespace {

    private static final String GB_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String AUTH_KEY = "&key=AIzaSyD1Z9BeavtwtIppdFp5T8XX83QnF9L50jc";
    private static final String SEARCH_STRING_QUERY_KEY = "q";
    private static final int BOOKS_PER_PAGE = 10;
    private static final String MAX_RESULTS_QUERY_KEY = "maxResults";
    private static final String START_INDEX_QUERY_KEY = "startIndex";
    private static final String PRINT_TYPE_KEY = "printType";

    public static String bookSearchUrl(String query) {
        String encodedQuery = "";
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error encoding URL parameters: " + e.getMessage());
        }
        return GB_URL+SEARCH_STRING_QUERY_KEY+"="+encodedQuery;
    }

    public static String bookSearchUrl(String query, int numberOfBooks) {
        String encodedQuery = "";
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error encoding URL parameters: " + e.getMessage());
        }
        return GB_URL+SEARCH_STRING_QUERY_KEY+"="+encodedQuery+"&"+START_INDEX_QUERY_KEY+"="+0+"&"+MAX_RESULTS_QUERY_KEY+"="+numberOfBooks+"&"+PRINT_TYPE_KEY+"=books"+AUTH_KEY;
    }
    
    public static String getAuthKey(){
        return AUTH_KEY;
    }
}

