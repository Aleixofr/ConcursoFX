package com.afro.concursofx.controller;

import com.afro.concursofx.model.Operacion;
import com.afro.concursofx.utils.Cronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConcursoController {

    @FXML
    private Button btn_jugar;

    @FXML
    private Button btn_resolver;

    @FXML
    private ListView<String> list_resultados;

    @FXML
    private ListView<String> list_clasificacion;

    @FXML
    private TextField tf_resultado;

    @FXML
    private TextField tf_nombre;

    @FXML
    private Text tv_operacion;

    @FXML
    private Text tv_crono;

    private Operacion operacion;

    private int puntos;

    /**
     * Metodo que se ejecuta al hacer clic en el boton "Jugar".
     * Inicializa los puntos, genera una nueva operacion, y configura un cronometro de 30 segundos.
     * Habilita y deshabilita los botones correspondientes segun el estado del cronometro.
     *
     * @action onClick - btnJugar
     * @param event el evento de accion asociado al clic en el boton "Jugar".
     */
    @FXML
    void actJugar(ActionEvent event) {
        // Reiniciar puntos al iniciar el juego
        puntos = 0;
        generarNuevaOperacion();

        /**
         * Configuracion del Cronometro
         */
        Cronometro.init(30); // Inicializa el cronometro con 30 segundos

        // Actualiza el texto del cronometro en cada tick
        Cronometro.setOnTick(() -> {
            tv_crono.setText("Tiempo restante: " + Cronometro.getTimeRemaining());
        });

        // Acciones a realizar cuando el tiempo del cronometro se termina
        Cronometro.setOnFinish(() -> {
            tv_crono.setText("\u00a1Tiempo terminado!");
            btn_resolver.setDisable(true); // Deshabilitar el boton "Resolver"
            btn_jugar.setDisable(false); // Habilitar el boton "Jugar"
            actualizarClasificacion(); // Actualizar la clasificacion
        });

        // Configurar estado inicial de los botones
        btn_resolver.setDisable(false); // Habilitar el boton "Resolver"
        btn_jugar.setDisable(true);    // Deshabilitar el boton "Jugar"

        // Iniciar el cronometro
        Cronometro.start();
    }

    @FXML
    void actResolver(ActionEvent event) {
        try {
            int respuestaUsuario = Integer.parseInt(tf_resultado.getText());
            if (respuestaUsuario == operacion.getResultado()) {
                list_resultados.getItems().add("Correcto: " + tv_operacion.getText() + " = " + respuestaUsuario);
                puntos++;
                Cronometro.init(Cronometro.getTimeRemaining() + 20);
            } else {
                list_resultados.getItems().add("Incorrecto: " + tv_operacion.getText() + " = " + operacion.getResultado());
                puntos--;
            }
            generarNuevaOperacion();
            tf_resultado.clear();
        } catch (NumberFormatException e) {
            list_resultados.getItems().add("Respuesta no válida");
        }
    }

    private void generarNuevaOperacion() {

        boolean operacionValida = false;

        while (!operacionValida) {
            operacion = new Operacion();
            int a = operacion.getA();
            int b = operacion.getB();

            // Asegurar que a >= b
            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
                operacion.setA(a);
                operacion.setB(b);
            }

            // Verificar si el resultado es par
            if (operacion.getResultado() % 2 == 0) {
                operacionValida = true;
            }
        }

        String texto = operacion.getA() + " " + operacion.getTipoOperacion().getSimbolo() + " " + operacion.getB() + "__" + operacion.getResultado();
        tv_operacion.setText(texto);
    }

    private void actualizarClasificacion() {
        list_clasificacion.getItems().add(tf_nombre.getText() + ": " + puntos);
    }
}