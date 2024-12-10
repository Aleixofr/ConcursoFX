module com.afro.concursofx {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.afro.concursofx.controller;
    //exports com.afro.concursofx.model;

    opens com.afro.concursofx to javafx.fxml;
}