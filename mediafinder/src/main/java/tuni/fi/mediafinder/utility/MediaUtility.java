package tuni.fi.mediafinder.utility;

import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.apimanager.http.APIManager;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MediaUtility {

    private static List<Media> getMediaByTitle(String query) {
        List<Media> medias = APIManager.searchBooks(query, 40).build();

        int totalMoviesPageResult = APIManager.searchMovies(query, 1).getTotalPages();
        System.out.println(totalMoviesPageResult);
        System.out.println(APIManager.searchBooks(query, 40).getTotalItems());
        if (totalMoviesPageResult > 2) {
            for (int i = 1; i <= 3; i++) {
                medias.addAll(APIManager.searchMovies(query, i).build());
            }
        } else {
            for (int i = 1; i <= totalMoviesPageResult; i++) {
                medias.addAll(APIManager.searchMovies(query, i).build());
            }
        }

        return medias;
    }

    public static List<Media> getMediasByQuery(String query,int startIndex,int endIndex) {
        List<Media> medias = getMediaByTitle(query).stream()
                                                     .filter(media -> Utility.checkDate(media.getReleaseDate()))
                                                   .filter(media->media.getRating()!=null)
                                                   .collect(Collectors.toList());
                                                //    .subList(startIndex, endIndex);

        return medias;
    }

}
