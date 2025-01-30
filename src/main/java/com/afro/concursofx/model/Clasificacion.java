package com.afro.concursofx.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Clasificacion implements  DataBaseOperations{

    int id;
    String usuario;
    String valor;


    public Clasificacion(int id, String usuario, String valor) {
        this.id = id;
        this.usuario = usuario;
        this.valor = valor;
    }

    public Clasificacion() {
    }


    /**
     * Getters & Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    //Override DataBaseOperations

    @Override
    public boolean insertarResultado(String usuario, int valor) {

        // Conectar a la base de datos y realizar la inserción
        try (Connection conn = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get())) {

            /**
             * @Query QUERY_INSERT_RESULTADO
             */
            try (PreparedStatement pstmt = conn.prepareStatement(DDBBQuery.QUERY_INSERT_RESULTADO.get())) {
                pstmt.setString(1, usuario);
                pstmt.setString(2, String.valueOf(valor));

                // Ejecutar la inserción
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Clasificacion obtenerResultado(int id) {

        Clasificacion resultado = null;

        // Conexión a la base de datos y ejecución de la consulta
        try {

            Connection conn = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get());

            /**
             * @Query QUERY_SELECT_W_ID_RESULTADO
             */
            try (PreparedStatement pstmt = conn.prepareStatement(DDBBQuery.QUERY_SELECT_W_ID_RESULTADO.get())) {
                pstmt.setString(1, String.valueOf(id));

                ResultSet rs = pstmt.executeQuery();

                // Verificar si el usuario fue encontrado
                if(rs.next()) {
                    resultado = new Clasificacion(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("valor")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public Clasificacion obtenerResultado(String usuario) {

        Clasificacion resultado = null;

        // Conexión a la base de datos y ejecución de la consulta
        try {

            Connection conn = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get());

            /**
             * @Query QUERY_SELECT_W_USUARIO_RESULTADO
             */
            try (PreparedStatement pstmt = conn.prepareStatement(DDBBQuery.QUERY_SELECT_W_USUARIO_RESULTADO.get())) {
                pstmt.setString(1, usuario);

                ResultSet rs = pstmt.executeQuery();

                // Verificar si el usuario fue encontrado
                if(rs.next()) {
                    resultado = new Clasificacion(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("valor")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public boolean eliminarResultado(int id) {
        return true;
    }

    @Override
    public List<Clasificacion> obtenerTodosResultados() {

        List<Clasificacion> lista = new ArrayList<>();
        Clasificacion resultado;

        try {

            Connection conn = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get());

            /**
             * @Query QUERY_SELECT_ALL_RESULTADO
             */
            PreparedStatement pstmt = conn.prepareStatement(DDBBQuery.QUERY_SELECT_ALL_RESULTADO.get());

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Verificar si el usuario fue encontrado
            while (rs.next()) {
                resultado = new Clasificacion(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("valor")
                );

                lista.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

        return lista;
    }
}
