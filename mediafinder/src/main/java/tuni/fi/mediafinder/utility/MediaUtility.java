package tuni.fi.mediafinder.utility;

import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.apimanager.http.APIManager;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MediaUtility {

    private static List<Media> getMediaByTitle(String query) {
        List<Media> medias = APIManager.searchBooks(query, 40).build();

        int totalMoviesPageResult = APIManager.searchMovies(query, 1).getTotalPages();
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

    public static List<Media> getMediasByQuery(String query, boolean isMovie, boolean isBook, LocalDate startDate, LocalDate endDate) {
        if(query == null || query.isEmpty()) {
            return new ArrayList<>();
        }
        List<Media> medias = getMediaByTitle(query).stream()
                .filter(media -> Utility.checkDate(media.getReleaseDate()))
                .filter(media -> media.getRating() != null)
                .map(media -> {
                    if (media.getMediaType().equals(Utility.MediaType.BOOK)) {
                        media.setRating(media.getRating() * 2);
                    }
                    return media;
                })
                .filter(media-> {
                    if (isMovie && isBook) {
                        return true;
                    } else if (isMovie) {
                        return media.getMediaType().equals(Utility.MediaType.MOVIE);
                    } else if (isBook) {
                        return media.getMediaType().equals(Utility.MediaType.BOOK);
                    } else {
                        return false;
                    }
                })
                .filter(media->{
                    if (startDate == null || endDate == null) {
                        return true;
                    } else {
                        return Utility.pasrseDate(media.getReleaseDate()).isAfter(startDate) && Utility.pasrseDate(media.getReleaseDate()).isBefore(endDate);
                    } 
                })
                .collect(Collectors.toList());
        // .subList(startIndex, endIndex);

        return medias;
    }

    public static Map<Utility.MediaType, Map<String, Long>> getMediaByRatings(String query) {

        Map<Utility.MediaType, Map<String, Long>> groupedByRatingRange = getMediaByTitle(query).stream()
                .filter(media -> Utility.checkDate(media.getReleaseDate()))
                .filter(media -> media.getRating() != null)
                .map(media -> {
                    if (media.getMediaType().equals(Utility.MediaType.BOOK)) {
                        media.setRating(media.getRating() * 2);
                    }
                    return media;
                })
                .collect(
                        Collectors.groupingBy(
                                Media::getMediaType,
                                Collectors.toMap(
                                        media -> getRatingRange(media.getRating()),
                                        media -> 1L,
                                        Long::sum,
                                        TreeMap::new // Use TreeMap to keep keys sorted
                                )));
        groupedByRatingRange.forEach((type, ratingCounts) -> {
            for (int i = 0; i <= 8; i += 2) {
                String range = i + "-" + (i + 2);
                ratingCounts.putIfAbsent(range, 0L);
            }
        });


        return groupedByRatingRange;
    }

    private static String getRatingRange(double rating) {
        if (rating <= 2) {
            return "0-2";
        } else if (rating <= 4) {
            return "2-4";
        } else if (rating <= 6) {
            return "4-6";
        } else if (rating <= 8) {
            return "6-8";
        } else if (rating <= 10) {
            return "8-10";
        } else {
            return "0-2";
        }
    }

    public static Map<Utility.MediaType, Map<String, Long>> getMediaByReleaseYear(String query) {

       Map<Utility.MediaType, Map<String, Long>> groupedByYear = getMediaByTitle(query).stream()
                .filter(media -> Utility.checkDate(media.getReleaseDate()))
                .filter(media -> media.getRating() != null)
                .map(media -> {
                    if (media.getMediaType().equals(Utility.MediaType.BOOK)) {
                        media.setRating(media.getRating() * 2);
                    }
                    return media;
                })
                .collect(
                        Collectors.groupingBy(
                                Media::getMediaType,
                                Collectors.toMap(
                                        media -> media.getReleaseDate().substring(0, 4),
                                        media -> 1L,
                                        Long::sum,
                                        TreeMap::new // Use TreeMap to keep keys sorted
                                )));
        


        return groupedByYear;
    }

}
