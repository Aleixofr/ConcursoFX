package com.afro.concursofx;

import com.afro.concursofx.controller.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConcursoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new InicioController().showEstaPantalla(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
