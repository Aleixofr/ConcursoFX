package com.afro.concursofx.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Cronometro {
    private static int t_inicial; // Tiempo inicial en segundos
    private static int t_restante; // Tiempo restante en segundos
    private static Timeline timeline; // Controlador del cronómetro
    private static Runnable onTick; // Acción a realizar en cada tick
    private static Runnable onFinish; // Acción a realizar cuando el cronómetro finalice

    public static void init(int initialTime) {
        Cronometro.t_inicial = initialTime;
        t_restante = initialTime;
    }


    /**
     * Configura la acción que se ejecutará en cada tick.
     *
     * @param onTick Acción a realizar en cada tick.
     */
    public static void setOnTick(Runnable onTick) {
        Cronometro.onTick = onTick;
    }

    /**
     * Configura la acción que se ejecutará cuando el cronómetro termine.
     *
     * @param onFinish Acción a realizar al finalizar.
     */
    public static void setOnFinish(Runnable onFinish) {
        Cronometro.onFinish = onFinish;
    }

    /**
     * Inicia el cronómetro.
     */
    public static void start() {
        stop(); // Detiene cualquier cronómetro previo

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
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Detiene el cronómetro.
     */
    public static void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Reinicia el cronómetro al tiempo inicial.
     */
    public static void reset() {
        stop();
        t_restante = t_restante;
    }

    /**
     * Obtiene el tiempo restante en segundos.
     *
     * @return Tiempo restante.
     */
    public static int getTimeRemaining() {
        return t_restante;
    }
}
