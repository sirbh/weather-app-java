package tuni.fi.mediafinder.apimanager.mapping;

public class GoogleBooksNamespace extends APINamespace {
    private static final String GB_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String AUTH = "";
    private static final String SEARCH_STRING_QUERY_KEY = "q";
    public static final int BOOKS_PER_PAGE = 40;
    public static final String SPACE_ENCODING = "%20";
    public String getBaseUrl() {
        return GB_URL;
    }

    public String getAuthenticationToken() {
        return AUTH;
    }

    public String getSearchStringQueryKey() {
        return SEARCH_STRING_QUERY_KEY;
    }

    public String getPageQuery(int pageNumber) {
        return "startIndex=" + pageNumber * BOOKS_PER_PAGE + "&maxResults=" + BOOKS_PER_PAGE;
    }
    public String getSpaceEncoding() {
        return SPACE_ENCODING;
    }
}
