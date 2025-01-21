package com.afro.concursofx.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Clase utilitaria que implementa un cronómetro configurable.
 * Permite definir un tiempo inicial, acciones a realizar en cada tick y al finalizar,
 * así como iniciar, detener y reiniciar el cronómetro.
 */
public class Cronometro {
    private static int t_inicial; // Tiempo inicial en segundos.
    private static int t_restante; // Tiempo restante en segundos.
    private static Timeline timeline; // Controlador del cronómetro.
    private static Runnable onTick; // Acción a realizar en cada tick.
    private static Runnable onFinish; // Acción a realizar cuando el cronómetro finalice.

    /**
     * Inicializa el cronómetro con un tiempo inicial dado.
     *
     * @param initialTime Tiempo inicial en segundos.
     */
    public static void init(int initialTime) {
        Cronometro.t_inicial = initialTime;
        t_restante = initialTime;
    }

    /**
     * Configura la acción que se ejecutará en cada tick (cada segundo).
     *
     * @param onTick Acción a realizar en cada tick.
     */
    public static void setOnTick(Runnable onTick) {
        Cronometro.onTick = onTick;
    }

    /**
     * Configura la acción que se ejecutará cuando el cronómetro termine.
     *
     * @param onFinish Acción a realizar al finalizar el cronómetro.
     */
    public static void setOnFinish(Runnable onFinish) {
        Cronometro.onFinish = onFinish;
    }

    /**
     * Inicia el cronómetro. Si ya existe un cronómetro activo, lo detiene antes de iniciar uno nuevo.
     */
    public static void start() {
        stop(); // Detiene cualquier cronómetro previo.

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            t_restante--;
            if (onTick != null) {
                onTick.run();
            }
            if (t_restante <= 0) {
                stop();
                if (onFinish != null) {
                    onFinish.run();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repite indefinidamente hasta que el tiempo termine.
        timeline.play();
    }

    /**
     * Detiene el cronómetro si está en ejecución.
     */
    public static void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Reinicia el cronómetro al tiempo inicial configurado.
     */
    public static void reset() {
        stop();
        t_restante = t_inicial;
    }

    /**
     * Obtiene el tiempo restante del cronómetro en segundos.
     *
     * @return Tiempo restante en segundos.
     */
    public static int getTimeRemaining() {
        return t_restante;
    }
}
