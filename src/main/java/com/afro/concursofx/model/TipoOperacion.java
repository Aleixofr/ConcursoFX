package com.afro.concursofx.model;

import java.util.Random;

/**
 * Enumeración que representa los tipos de operaciones matemáticas disponibles.
 * Cada tipo de operación tiene un símbolo asociado y una implementación específica
 * para calcular el resultado con dos operandos.
 */
public enum TipoOperacion {
    /**
     * Operación de suma, representada por el símbolo "+".
     */
    SUMA("+") {
        @Override
        public int calcular(int a, int b) {
            return a + b;
        }
    },

    /**
     * Operación de resta, representada por el símbolo "-".
     * Si el segundo operando es mayor que el primero, intercambia los operandos
     * antes de realizar la operación para evitar resultados negativos.
     */
    RESTA("-") {
        @Override
        public int calcular(int a, int b) {
            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
            }
            return a - b;
        }
    },

    /**
     * Operación de multiplicación, representada por el símbolo "*".
     */
    MULTIPLICACION("*") {
        @Override
        public int calcular(int a, int b) {
            return a * b;
        }
    },

    /**
     * Operación de división, representada por el símbolo "/".
     * Si el divisor es cero, se ajusta a uno para evitar divisiones por cero.
     * Además, si el divisor es mayor que el dividendo, los operandos se intercambian.
     */
    DIVISION("/") {
        @Override
        public int calcular(int a, int b) {
            if (b == 0) {
                b = 1; // Evita la división por cero.
            }
            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
            }
            return a / b;
        }
    };

    private String simbolo;

    /**
     * Constructor que inicializa el tipo de operación con su símbolo correspondiente.
     *
     * @param simbolo el símbolo asociado al tipo de operación.
     */
    TipoOperacion(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtiene el símbolo asociado al tipo de operación.
     *
     * @return el símbolo como una cadena de texto.
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Calcula el resultado de la operación utilizando los operandos dados.
     * Este método debe ser implementado por cada tipo de operación.
     *
     * @param a el primer operando.
     * @param b el segundo operando.
     * @return el resultado de la operación.
     */
    public abstract int calcular(int a, int b);

    /**
     * Selecciona aleatoriamente un tipo de operación de entre las disponibles.
     *
     * @return una instancia aleatoria de {@link TipoOperacion}.
     */
    public static TipoOperacion getOperacionAleatoria() {
        TipoOperacion[] operaciones = values();
        Random random = new Random();
        return operaciones[random.nextInt(operaciones.length)];
    }
}
