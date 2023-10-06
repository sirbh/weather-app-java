/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.apimanager;

/**
 *
 * @author knsach
 */
public class GoogleBooksAPIManager {

    private static final String GB_URL = "https://www.googleapis.com/books/v1/volumes?";

    public static String getBooksByTitle(String query, int startIndex, int totalBooks) {
        String uri = GB_URL;
        uri = uri + "q=" + query + "&startIndex=" + startIndex + "&maxResults=" + totalBooks;
        String response = "";
        try {
            response = APIClient.get(uri);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }
}

