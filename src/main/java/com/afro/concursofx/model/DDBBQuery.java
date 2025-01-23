package com.afro.concursofx.model;

public enum DDBBQuery {

    URL_DATABASE("jdbc:sqlite:data/bbdd_concurso.db"),

    QUERY_INSERT_RESULTADO("INSERT INTO resultado (usuario, valor) VALUES (?, ?)"),

    QUERY_SELECT_W_ID_RESULTADO("SELECT * FROM resultado WHERE id = ?"),

    QUERY_SELECT_W_USUARIO_RESULTADO("SELECT * FROM resultado WHERE usuario = ?"),

    QUERY_SELECT_ALL_RESULTADO("SELECT * FROM resultado");


    //ATRIBUTOS
    private String descripcion;

    DDBBQuery(String descripcion) { this.descripcion = descripcion; }

    public String getDescripcion() { return descripcion; }
}
