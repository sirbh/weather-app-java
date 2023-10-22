package tuni.fi.mediafinder.apimanager.mapping;

import tuni.fi.mediafinder.models.Book;
import tuni.fi.mediafinder.models.Media;

import java.util.ArrayList;
import java.util.List;

public class GoogleBooksResponse extends APIResponse {
    private int totalItems;
    private String kind;
    private ArrayList<BookItem> items;

    private static class BookItem {
        private String id;
        private VolumeInfo volumeInfo;
        public String getId() {
            return this.id;
        }
        public String getTitle() {
            return this.volumeInfo.getTitle();
        }
        public String getDescription() { return this.volumeInfo.getDescription(); }
        public String getPublishedDate() {
            return this.volumeInfo.getPublishedDate();
        }
        public Double getRating() {
            return this.volumeInfo.getRating();
        }

        public String toString() {
            return "volumeInfo={ " + volumeInfo + " }";
        }
    }

    private static class VolumeInfo {
        private String title;
        private ArrayList<String> authors;
        private String publishedDate;
        private String description;
        private String maturityRating;
        private ImageLinks imageLinks;
        private String language;
        private Double averageRating;
        private int ratingsCount;

        public String getTitle() {
            return title;
        }
        public String getDescription() { return description; }
        public String getPublishedDate() {
            return publishedDate;
        }
        public Double getRating() {
            return averageRating;
        }

        public String toString() {
            return "title=" + title + ", authors=" + String.join(", ", authors) +
                    ", publishedDate=" + publishedDate + ", description=" + description.substring(0, 20) +
                    ", maturityRating=" + maturityRating + ", imageLinks=" + imageLinks;
        }
    }

    private static class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;

        public String toString() {
            return "{ smallThumbnail=" + smallThumbnail + ", thumbnail=" + thumbnail + "}";
        }
    }

    public String toString() {
        return "MyData{" +
                "totalItems=" + totalItems +
                ", kind='" + kind + '\'' +
                ", items=" + items +
                '}';
    }

    public ArrayList<Media> build() {
        ArrayList<Media> books = new ArrayList<>();
        for (BookItem item : items) {
            Book book = new Book(item.getId(), item.getTitle(), item.getDescription(), item.getPublishedDate(), item.getRating());
            books.add(book);
        }
        return books;
    }
}
