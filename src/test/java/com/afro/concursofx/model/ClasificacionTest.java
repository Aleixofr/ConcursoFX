package com.afro.concursofx.model;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClasificacionTest {

    private static Connection connection;
    private Clasificacion clasificacion;

    @BeforeAll
    static void setupDatabase() throws Exception {
        // Conexión a la BD en memoria
        connection = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get());
    }

    @BeforeEach
    void setup() {
        clasificacion = new Clasificacion();
    }

    @AfterEach
    void cleanup() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM resultado");
        }
    }

    @AfterAll
    static void teardown() throws SQLException {
        connection.close();
    }

    @Test
    void testInsertarResultado() {

        Clasificacion clasificacion = new Clasificacion("usuarioTest", "100");

        boolean resultado = clasificacion.insertarResultado(clasificacion);
        assertTrue(resultado, "El resultado debería insertarse correctamente en la base de datos.");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM resultado WHERE usuario = 'usuarioTest'")) {

            assertTrue(rs.next(), "El usuario debería existir en la base de datos.");
            assertEquals("100", rs.getString("valor"));

        } catch (SQLException e) {
            fail("Error al consultar la base de datos: " + e.getMessage());
        }
    }

    @Test
    void testObtenerResultadoPorId() throws SQLException {
        int id = insertarUsuarioPrueba("usuarioTest", "150");

        Clasificacion resultado = clasificacion.obtenerResultado(id);
        assertNotNull(resultado);
        assertEquals("usuarioTest", resultado.getUsuario());
        assertEquals("150", resultado.getValor());
    }

    @Test
    void testObtenerResultadoPorUsuario() throws SQLException {
        insertarUsuarioPrueba("usuarioTest", "200");

        Clasificacion resultado = clasificacion.obtenerResultado("usuarioTest");
        assertNotNull(resultado);
        assertEquals("usuarioTest", resultado.getUsuario());
        assertEquals("200", resultado.getValor());
    }

    @Test
    void testObtenerTodosResultados() throws SQLException {
        insertarUsuarioPrueba("usuario1", "300");
        insertarUsuarioPrueba("usuario2", "400");

        List<Clasificacion> resultados = clasificacion.obtenerTodosResultados();
        assertEquals(2, resultados.size());
        assertEquals("usuario1", resultados.get(0).getUsuario());
        assertEquals("usuario2", resultados.get(1).getUsuario());
    }

    // 🛠️ Métodos Auxiliares
    private int insertarUsuarioPrueba(String usuario, String valor) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO resultado (usuario, valor) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, valor);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
}
