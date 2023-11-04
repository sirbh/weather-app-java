/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author knsach
 */
public class Utility {
    public static enum MediaType {
        BOOK,
        MOVIE
    }

    public static boolean checkDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
