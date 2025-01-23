package com.afro.concursofx.model;

import java.util.List;

public interface DataBaseOperations {

    // Insertar un resultado
    boolean insertarResultado(int id, String usuario, Long valor);

    // Obtener un resultado
    Clasificacion obtenerResultado(int id);

    Clasificacion obtenerResultado(String usuario);


    // Eliminar un resultado
    boolean eliminarResultado(int id);

    // Obtener todos los resultados
    List<Clasificacion> obtenerTodosResultados();

}
