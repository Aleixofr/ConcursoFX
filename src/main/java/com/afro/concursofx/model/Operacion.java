package com.afro.concursofx.model;

import java.util.Random;

/**
 * Representa una operación matemática con dos operandos y un tipo de operación.
 * La operación puede generarse aleatoriamente, incluyendo los valores de los operandos
 * y el tipo de operación.
 */
public class Operacion {

    private int a; // Primer operando de la operación.
    private int b; // Segundo operando de la operación.
    private TipoOperacion tipoOperacion; // Tipo de operación (suma, resta, etc.).

    /**
     * Constructor que genera una operación aleatoria.
     * Los operandos y el tipo de operación se generan utilizando valores aleatorios.
     */
    public Operacion() {
        Random random = new Random();
        this.a = random.nextInt(100); // Genera un número aleatorio entre 0 y 99 para 'a'.
        this.b = random.nextInt(100); // Genera un número aleatorio entre 0 y 99 para 'b'.
        this.tipoOperacion = TipoOperacion.getOperacionAleatoria(); // Obtiene un tipo de operación aleatorio.
    }

    /**
     * Obtiene el valor del primer operando.
     *
     * @return el valor del operando 'a'.
     */
    public int getA() {
        return a;
    }

    /**
     * Establece el valor del primer operando.
     *
     * @param a el nuevo valor del operando 'a'.
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Obtiene el valor del segundo operando.
     *
     * @return el valor del operando 'b'.
     */
    public int getB() {
        return b;
    }

    /**
     * Establece el valor del segundo operando.
     *
     * @param b el nuevo valor del operando 'b'.
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Obtiene el tipo de operación.
     *
     * @return el tipo de operación ({@link TipoOperacion}).
     */
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Obtiene el símbolo asociado al tipo de operación.
     *
     * @return el símbolo del tipo de operación (por ejemplo, "+" para suma).
     */
    public String getSimbolo() {
        return tipoOperacion.getSimbolo();
    }

    /**
     * Establece el tipo de operación.
     *
     * @param tipoOperacion el nuevo tipo de operación.
     */
    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * Calcula el resultado de la operación utilizando los operandos y el tipo de operación.
     *
     * @return el resultado de la operación.
     */
    public int getResultado() {
        return tipoOperacion.calcular(a, b);
    }
}
