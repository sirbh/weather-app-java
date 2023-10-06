module tuni.fi.mediafinder {
    requires javafx.controls;
    requires javafx.fxml;

    opens tuni.fi.mediafinder to javafx.fxml;
    exports tuni.fi.mediafinder;
}
