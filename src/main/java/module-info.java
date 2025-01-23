module com.afro.concursofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    exports com.afro.concursofx;
    opens com.afro.concursofx.controller to javafx.fxml;
}