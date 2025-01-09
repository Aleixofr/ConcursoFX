module com.afro.concursofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.afro.concursofx;
    opens com.afro.concursofx.controller to javafx.fxml;
}