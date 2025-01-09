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

    @FXML
    void actJugar(ActionEvent event) {
        puntos = 0; // Reiniciar puntos al iniciar
        generarNuevaOperacion();

        /**
         * Cronometro
         */
        Cronometro.init(30);

        Cronometro.setOnTick(() -> {
            tv_crono.setText("Tiempo restante: " + Cronometro.getTimeRemaining());
        });

        Cronometro.setOnFinish(() -> {
            tv_crono.setText("\u00a1Tiempo terminado!");
            btn_resolver.setDisable(true);
            btn_jugar.setDisable(false);
            actualizarClasificacion();
        });

        btn_resolver.setDisable(false);
        btn_jugar.setDisable(true);
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