module tuni.fi.mediafinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    opens tuni.fi.mediafinder to javafx.fxml;
    opens tuni.fi.mediafinder.controllers to javafx.fxml;
    exports tuni.fi.mediafinder;
    exports tuni.fi.mediafinder.controllers;
    requires org.json;
    requires com.google.gson;
    opens tuni.fi.mediafinder.apimanager.mapping to com.google.gson;
    opens tuni.fi.mediafinder.models to javafx.base;
}
