package tuni.fi.mediafinder.apimanager.mapping;

import tuni.fi.mediafinder.models.Media;

import java.util.ArrayList;

public abstract class APIResponse {
    public abstract ArrayList<Media> build();
}
