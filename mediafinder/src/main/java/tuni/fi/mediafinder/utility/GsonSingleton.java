/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuni.fi.mediafinder.utility;

import com.google.gson.Gson;

/**
 *
 * @author knsach
 */
public class GsonSingleton {

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
