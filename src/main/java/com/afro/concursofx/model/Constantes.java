package com.afro.concursofx.model;

/**
 * Enumeración que define constantes utilizadas en la aplicación.
 * Estas constantes incluyen rutas de vistas, estilos y títulos de las ventanas
 * del concurso matemático.
 */
public enum Constantes {

    // GLOBAL
    /**
     * Ruta del archivo FXML para la pantalla de inicio.
     */
    PAGINA_INICIO("inicio_view.fxml"),

    /**
     * Ruta del archivo FXML para la pantalla del concurso.
     */
    PAGINA_CONCURSO("concurso_view.fxml"),

    /**
     * Ruta del archivo CSS para los estilos de la aplicación.
     */
    RUTA_ESTILOS("/css/styles.css"),

    // INICIO
    /**
     * Título de la ventana para la pantalla de inicio.
     */
    TITULO_INICIO("CONCURSO MATEMATICO"),

    /**
     * Título de la ventana para la pantalla del concurso.
     */
    TITULO_CONCURSO("CONCURSO MATEMATICO");

    // ATRIBUTOS
    private final String description;

    /**
     * Constructor para inicializar una constante con su descripción.
     *
     * @param description la descripción asociada a la constante.
     */
    Constantes(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripción asociada a una constante.
     *
     * @return la descripción como una cadena de texto.
     */
    public String getDescription() {
        return description;
    }
}
