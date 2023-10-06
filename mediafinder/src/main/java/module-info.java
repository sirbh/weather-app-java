module tuni.fi.mediafinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    opens tuni.fi.mediafinder to javafx.fxml;
    exports tuni.fi.mediafinder;
}
