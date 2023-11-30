package tuni.fi.mediafinder.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tuni.fi.mediafinder.models.Preference;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtil {
    private static final String JSON_FILE_PATH = "preferences.json";

    public static void savePreferences(Preference preference) {
        Gson gson = GsonSingleton.getGson();

        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(preference, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Preference loadPreferences() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return gson.fromJson(reader, Preference.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}