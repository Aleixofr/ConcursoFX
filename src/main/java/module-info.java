module com.afro.concursofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires kernel;
    requires layout;
    requires java.desktop;

    exports com.afro.concursofx;
    opens com.afro.concursofx.controller to javafx.fxml;
    exports reports;
}