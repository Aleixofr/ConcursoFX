package com.afro.concursofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConcursoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConcursoApplication.class.getResource("concurso_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Concurso De Calculo");

        // Aplicar el archivo CSS
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}
