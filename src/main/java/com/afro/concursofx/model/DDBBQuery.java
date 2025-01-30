package com.afro.concursofx.model;

/**
 * Enumeración que contiene las consultas SQL y la URL de la base de datos.
 * Estas consultas se utilizan para interactuar con la base de datos del concurso.
 */
public enum DDBBQuery {

    /**
     * URL de conexión a la base de datos SQLite.
     */
    URL_DATABASE("jdbc:sqlite:data/bbdd_concurso.db"),

    /**
     * Consulta SQL para insertar un nuevo resultado en la tabla 'resultado'.
     * Los parámetros son: usuario y valor.
     */
    QUERY_INSERT_RESULTADO("INSERT INTO resultado (usuario, valor) VALUES (?, ?)"),

    /**
     * Consulta SQL para seleccionar un resultado de la tabla 'resultado' por su ID.
     * El parámetro es: id.
     */
    QUERY_SELECT_W_ID_RESULTADO("SELECT * FROM resultado WHERE id = ?"),

    /**
     * Consulta SQL para seleccionar resultados de la tabla 'resultado' por el nombre de usuario.
     * El parámetro es: usuario.
     */
    QUERY_SELECT_W_USUARIO_RESULTADO("SELECT * FROM resultado WHERE usuario = ?"),

    /**
     * Consulta SQL para seleccionar todos los resultados de la tabla 'resultado'.
     */
    QUERY_SELECT_ALL_RESULTADO("SELECT * FROM resultado");


    // ATRIBUTOS
    private String descripcion;

    /**
     * Constructor privado para inicializar la descripción de cada consulta.
     *
     * @param descripcion La descripción o consulta SQL asociada a la constante.
     */
    DDBBQuery(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método para obtener la descripción o consulta SQL asociada a la constante.
     *
     * @return La descripción o consulta SQL.
     */
    public String get() {
        return descripcion;
    }
}
