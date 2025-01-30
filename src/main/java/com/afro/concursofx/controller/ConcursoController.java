package com.afro.concursofx.controller;

import com.afro.concursofx.model.Clasificacion;
import com.afro.concursofx.model.Constantes;
import com.afro.concursofx.model.Operacion;
import com.afro.concursofx.utils.Cronometro;
import com.afro.concursofx.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controlador principal para la pantalla del concurso.
 * Maneja la interacción del usuario con los elementos de la interfaz,
 * como botones, listas y campos de texto. Además, controla la lógica
 * del juego, incluyendo la generación de operaciones, el cronómetro y
 * la actualización de la clasificación.
 */
public class ConcursoController {

    @FXML
    private Button btn_jugar;

    @FXML
    private Button btn_resolver;

    @FXML
    private ListView<String> list_clasificacion;

    @FXML
    private ListView<String> list_resultados;

    @FXML
    private TextField tf_resultado;

    @FXML
    private Text tv_crono;

    @FXML
    private Text tv_operacion;

    @FXML
    private Text tv_usuario;

    private Operacion operacion;

    private int puntos;

    private String usuario = "";

    /**
     * Inicializa los componentes que tienen algún tipo de dato o lógica.
     * Este método se ejecuta automáticamente cuando se carga la interfaz.
     */
    @FXML
    public void initialize() {
        // Método vacío, preparado para inicialización futura si es necesario.
    }

    /**
     * Muestra la pantalla controlada por este controlador.
     *
     * @param stage el escenario donde se mostrará la pantalla.
     * @return la instancia de {@link ConcursoController} asociada a la pantalla.
     * @throws IOException si ocurre un error al cargar la pantalla.
     */
    public ConcursoController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_CONCURSO.getDescription(), Constantes.TITULO_CONCURSO.getDescription(), 600, 400);
        return fxmlLoader.getController();
    }

    /**
     * Actualiza el valor del usuario actual en el campo de texto correspondiente.
     *
     * @param text el nombre del usuario a establecer.
     */
    public void setTextFromMain(String text) {
        usuario = text.toUpperCase();
        tv_usuario.setText("JUGANDO: " + usuario);
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Jugar".
     * Inicializa los puntos, genera una nueva operación, y configura un cronómetro de 30 segundos.
     * Actualiza el estado de los botones según corresponda.
     *
     * @param event el evento de acción asociado al clic en el botón "Jugar".
     */
    @FXML
    void actJugar(ActionEvent event) {
        puntos = 0;
        list_resultados.getItems().clear();
        generarNuevaOperacion();

        Cronometro.init(30); // Inicializa el cronómetro con 30 segundos.

        Cronometro.setOnTick(() -> {
            tv_crono.setText("Tiempo restante: " + Cronometro.getTimeRemaining());
        });

        Cronometro.setOnFinish(() -> {
            tv_crono.setText("¡Tiempo terminado!");
            btn_resolver.setDisable(true);
            btn_jugar.setDisable(false);
            actualizarClasificacion(usuario, puntos);
        });

        btn_resolver.setDisable(false);
        btn_jugar.setDisable(true);

        Cronometro.start();
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Resolver".
     * Evalúa la respuesta del usuario, actualiza los puntos y genera una nueva operación.
     *
     * @param event el evento de acción asociado al clic en el botón "Resolver".
     */
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

    /**
     * Genera una nueva operación matemática para el usuario.
     * Asegura que la operación sea válida según las reglas definidas:
     * - Los operandos están en orden descendente.
     * - El resultado es un número par.
     */
    private void generarNuevaOperacion() {
        boolean operacionValida = false;

        while (!operacionValida) {
            operacion = new Operacion();
            int a = operacion.getA();
            int b = operacion.getB();

            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
                operacion.setA(a);
                operacion.setB(b);
            }

            if (operacion.getResultado() % 2 == 0) {
                operacionValida = true;
            }
        }

        String texto = operacion.getA() + " " + operacion.getTipoOperacion().getSimbolo() + " " + operacion.getB();
        tv_operacion.setText(texto);
    }

    public void iniciarClasificacion() {
        Clasificacion clasificacion = new Clasificacion();

        List<Clasificacion> clasificacionList = clasificacion.obtenerTodosResultados();

        list_clasificacion.getItems().clear(); // Limpiar antes de actualizar

        for (Clasificacion c : clasificacionList) {
            String usuario = c.getUsuario();
            String puntos = c.getValor();
            list_clasificacion.getItems().add(usuario + ": " + puntos);
        }
    }

    /**
     * Inserta un nuevo resultado en la base de datos y actualiza la lista de clasificación.
     */
    private void actualizarClasificacion(String usuario, int puntos) {
        Clasificacion clasificacion = new Clasificacion();

        // Intentar insertar en la base de datos
        boolean resultadoInsertado = clasificacion.insertarResultado(usuario, puntos);

        if (resultadoInsertado) {
            System.out.println("Resultado insertado correctamente.");
            iniciarClasificacion(); // Refrescar la lista
        } else {
            System.out.println("Error al insertar resultado en la base de datos.");
        }
    }
}
