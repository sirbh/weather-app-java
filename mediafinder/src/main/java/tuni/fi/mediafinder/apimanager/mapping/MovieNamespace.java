package tuni.fi.mediafinder.apimanager.mapping;

public class MovieNamespace extends APINamespace {
    private static final String TMDB_URL = "https://api.themoviedb.org/3/search/movie?";
    private static final String TMDB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDExNjc2YzZmN2IyOGYyOWMyZTk2YjhjNDYxNDQwOCIsInN1YiI6IjY0ZjM3MTMyNWYyYjhkMDExYjRkZmVkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TcSKCjiHCepxIyCzJa0X3gsZITVx4tPLSAiqWIYD49g";
    private static final String SEARCH_STRING_QUERY_KEY = "query";
    public static final int MOVIES_PER_PAGE = 20;

    public String getBaseUrl() {
        return TMDB_URL;
    }

    public String getAuthenticationToken() {
        return TMDB_API_KEY;
    }

    public String getSearchStringQueryKey() {
        return SEARCH_STRING_QUERY_KEY;
    }

    public String getPageQuery(int pageNumber) {
        return "page=" + pageNumber;
    }
}
