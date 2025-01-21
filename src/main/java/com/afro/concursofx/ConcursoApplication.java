package com.afro.concursofx;

import com.afro.concursofx.controller.InicioController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal para la aplicación de concurso matemático.
 * Extiende la clase {@link javafx.application.Application} de JavaFX para iniciar
 * y gestionar el ciclo de vida de la aplicación.
 */
public class ConcursoApplication extends Application {

    /**
     * Método principal que inicia la aplicación JavaFX.
     * Muestra la pantalla de inicio utilizando el controlador {@link InicioController}.
     *
     * @param stage El escenario principal donde se mostrará la interfaz gráfica.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        new InicioController().showEstaPantalla(stage);
    }

    /**
     * Método principal que inicia la ejecución de la aplicación.
     * Llama al método {@link #launch()} de la clase {@link Application} para comenzar
     * el ciclo de vida de la aplicación JavaFX.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch();
    }
}
