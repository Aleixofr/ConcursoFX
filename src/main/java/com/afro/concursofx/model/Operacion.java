package com.afro.concursofx.model;

import java.util.Random;

public class Operacion {

    private int a;
    private int b;
    private TipoOperacion tipoOperacion;

    // Constructor que genera una operacion aleatoria
    public Operacion() {
        Random random = new Random();
        this.a = random.nextInt(100);
        this.b = random.nextInt(100);
        this.tipoOperacion = TipoOperacion.getOperacionAleatoria();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public String getSimbolo() {
        return tipoOperacion.getSimbolo();
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public int getResultado() {
        return tipoOperacion.calcular(a, b);
    }
}
