module tuni.fi.mediafinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    opens tuni.fi.mediafinder to javafx.fxml;
    exports tuni.fi.mediafinder;
}
