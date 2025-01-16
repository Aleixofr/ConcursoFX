package com.afro.concursofx.utils;

import com.afro.concursofx.ConcursoApplication;
import com.afro.concursofx.model.Constantes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaUtils {

    /**
     * Metodo genérico para abrir una pantalla, dependiendo de la ruta de la vista pasada
     * por parámetro
     *
     * @param stage le pasamos un stage
     * @param vista le pasamos la vista que se va a cargar
     */
    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConcursoApplication.class.getResource(vista));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        stage.setTitle(titulo);
        scene.getStylesheets().add(getClass().getResource(Constantes.RUTA_ESTILOS.getDescription()).toExternalForm());
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    /**
     * Este método cierra la pantalla actual, a partir del stage actual.
     * El stage se obtiene del botón que se accionó en el momento.
     * Queda genérico para cualquier botón de esta pantalla.
     *
     * @param botonDelAction el pasamos el botón que accionó
     */
    public Stage cerrarEstaPantalla(Button botonDelAction){
        //OBTENEMOS EL STAGE DE LA PANTALLA ACTUAL, A PARTIR DEL BOTÓN QUE SE ACCIONA
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();

        //SE CIERRA EL STAGE
        stageAhora.close();

        //DEVUELVE EL STAGE PARA PODER SER REUTILIZADO EN OTRA PANTALLA POR EJEMPLO.
        return stageAhora;
    }
}
