package com.afro.concursofx.controller;

import com.afro.concursofx.model.Constantes;
import com.afro.concursofx.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la pantalla inicial de la aplicación.
 * Maneja la interacción del usuario con los elementos de la interfaz, como el botón para
 * iniciar el juego y el campo de texto para ingresar el nombre del usuario.
 */
public class InicioController {

        @FXML
        private ImageView inicio_bg;

        @FXML
        private Button inicio_btn;

        @FXML
        private TextField inicio_tf_nombre;

        @FXML
        private Text inicio_titulo;

        /**
         * Muestra la pantalla inicial controlada por esta clase.
         *
         * @param stage el escenario donde se mostrará la pantalla inicial.
         * @return la instancia de {@link InicioController} asociada a la pantalla.
         * @throws IOException si ocurre un error al cargar la pantalla.
         */
        public InicioController showEstaPantalla(Stage stage) throws IOException {
                FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(
                        stage,
                        Constantes.PAGINA_INICIO.getDescription(),
                        Constantes.TITULO_INICIO.getDescription(),
                        600,
                        400
                );
                return fxmlLoader.getController();
        }

        /**
         * Gestiona el evento del botón para abrir la segunda pantalla.
         * Este método obtiene el texto ingresado por el usuario en el campo de texto,
         * cierra la pantalla actual y abre la segunda pantalla pasando el nombre del usuario.
         *
         * @param event el evento de acción generado al hacer clic en el botón.
         */
        @FXML
        private void handleButtonAction(ActionEvent event) {
                try {
                        // Obtiene el escenario actual a través del botón y lo cierra.
                        Stage stage = new PantallaUtils().cerrarEstaPantalla(inicio_btn);

                        // Muestra la segunda pantalla y obtiene su controlador.
                        ConcursoController controller = new ConcursoController().showEstaPantalla(stage);

                        // Pasa el nombre del usuario ingresado en el campo de texto a la segunda pantalla.
                        controller.setTextFromMain(inicio_tf_nombre.getText());
                        controller.iniciarClasificacion();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
