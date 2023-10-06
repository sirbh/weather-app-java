module tuni.fi.mediafinderpro {
    requires javafx.controls;
    requires javafx.fxml;

    opens tuni.fi.mediafinderpro to javafx.fxml;
    exports tuni.fi.mediafinderpro;
}
