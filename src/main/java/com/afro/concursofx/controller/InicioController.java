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
         * ESte método permite visualizar la pantalla que controla este controlador.
         *
         * @param stage se le pasa un stage
         * @throws IOException se lanza una excepción de entrada/salida
         */
        public InicioController showEstaPantalla(Stage stage) throws IOException {
                FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIO.getDescription(),Constantes.TITULO_INICIO.getDescription(),600,400);
                //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
                InicioController controller = fxmlLoader.getController();

                return controller;
        }

        /**
         * Este método gestiona el evento del botón abrir la segunda pantalla
         * y actualiza el componente texto de la segunda ventana a partir de
         * lo que ha introducido el usuario en esta pantalla
         * ESTO ES UN EJEMPLO DE PASAR DATOS DE UNA PANTALLA A OTRA
         *
         * @param event
         */
        @FXML
        private void handleButtonAction(ActionEvent event) {
                try {
                        //OBTENEMOS EL STAGE DE ESTA PANTALLA A TRAVÉS DEL BOTÓN DEL ACTION Y CERRAMOS
                        Stage stage = new PantallaUtils().cerrarEstaPantalla(inicio_btn);

                        //MOSTRAMOS LA SEGUNDA PANTALLA Y OBTENEMOS EL CONTROLADOR PARA REFRESCAR EL CAMPO TEXTO
                        ConcursoController controller = new ConcursoController().showEstaPantalla(stage);

                        //PASAMOS EL CONTENIDO QUE HA INTRODUCIDO EL USUARIO EN EL TEXTFIELD DE ESTA PANTALLA
                        //A LA SEGUNDA PANTALLA
                        controller.setTextFromMain(inicio_tf_nombre.getText());

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }




}


