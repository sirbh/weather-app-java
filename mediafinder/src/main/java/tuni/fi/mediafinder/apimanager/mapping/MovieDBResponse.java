package tuni.fi.mediafinder.apimanager.mapping;

import tuni.fi.mediafinder.models.Book;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.models.Movie;

import java.util.ArrayList;

public class MovieDBResponse extends APIResponse {
    private int page;
    private ArrayList<MovieItem> results = new ArrayList<>();

    private static class MovieItem {
        private String getId() {
            return this.id;
        }
        private String getTitle() {
            return this.title;
        }
        private String getOverview() {
            return this.overview;
        }
        private String getReleaseDate() {
            return this.release_date;
        }
        private Double getVoteAverage() {
            return this.vote_average;
        }
        private boolean adult;
        private String backdrop_path;
        private ArrayList<Integer> genre_ids;
        private String id;
        private String original_language;
        private String title;
        private String overview;
        private Double popularity;
        private String poster_path;
        private String release_date;
        private Double vote_average;
        private int vote_count;
    }

    public ArrayList<Media> build() {
        ArrayList<Media> movies = new ArrayList<>();
        for (MovieDBResponse.MovieItem item : results) {
            Movie movie = new Movie(item.getId(), item.getTitle(), item.getOverview(), item.getReleaseDate(), item.getVoteAverage());
            movies.add(movie);
        }
        return movies;
    }
}
