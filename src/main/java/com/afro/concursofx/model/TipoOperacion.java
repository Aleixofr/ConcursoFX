package com.afro.concursofx.model;

import java.util.Random;

public enum TipoOperacion {
    SUMA("+") {
        @Override
        public int calcular(int a, int b) {
            return a + b;
        }
    },
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
    MULTIPLICACION("*") {
        @Override
        public int calcular(int a, int b) {
            return a * b;
        }
    },
    DIVISION("/") {
        @Override
        public int calcular(int a, int b) {
            if (b == 0) {
                b = 1; // Evita la división por cero
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

    TipoOperacion(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public abstract int calcular(int a, int b);

    // Método para seleccionar una operación aleatoria
    public static TipoOperacion getOperacionAleatoria() {
        TipoOperacion[] operaciones = values();
        Random random = new Random();
        return operaciones[random.nextInt(operaciones.length)];
    }
}
