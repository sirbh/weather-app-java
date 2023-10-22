module tuni.fi.mediafinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    opens tuni.fi.mediafinder to javafx.fxml;
    exports tuni.fi.mediafinder;
    requires org.json;
    requires com.google.gson;
    opens tuni.fi.mediafinder.apimanager.mapping to com.google.gson;
}
