package com.afro.concursofx.model;

public enum Constantes {
    //GLOBAL
    PAGINA_INICIO("inicio_view.fxml"),
    PAGINA_CONCURSO("concurso_view.fxml"),
    RUTA_ESTILOS("/css/styles.css"),

    //INICIO
    TITULO_INICIO("CONCURSO MATEMATICO"),
    TITULO_CONCURSO("CONCURSO MATEMATICO")

    //CONCURSO

    ;

    private final String description;

    Constantes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
