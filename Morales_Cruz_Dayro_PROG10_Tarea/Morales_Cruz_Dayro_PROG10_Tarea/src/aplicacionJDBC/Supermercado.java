package aplicacionJDBC;

import util.Color;
import H2.H2JDBC;
import java.sql.*;

/**
 * Clase que contiene métodos estáticos para realizar diversas consultas y
 * operaciones sobre la base de datos del supermercado, tales como listar
 * productos, empleados, y calcular valores totales relacionados con los
 * productos y empleados de diferentes secciones.
 *
 * @author Dayro Morales Cruz
 * @version abril/2025
 */
public class Supermercado {

    // Métodos para productos (ya implementados previamente) ...
    public static String selectAllProductosOrderBy(String orderBy) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Productos ordenados por %s%n", orderBy));

        // Definir el formato de las columnas
        String formatoCabecera = "%-6s %-40s %9s %6s %-7s%n";
        String formatoFila = "%-6s %-40s %9.2f %6d %7s%n";

        // Agregar cabecera
        resultadoSelect.append(Color.azul(String.format(formatoCabecera,
                "CÓDIGO",
                "DESCRIPCIÓN",
                "PRECIO",
                "STOCK",
                "SECCIÓN")));

        String sentenciaSQL = "SELECT * FROM producto ORDER BY " + orderBy + " ASC";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(String.format(formatoFila,
                        rs.getString("id_producto"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock_actual"),
                        rs.getString("id_seccion")));
            }
        } catch (SQLException ex) {
            resultadoSelect.append(Color.rojo(String.format("ERROR al ordenar por %s.%n", orderBy)));
        }
        return resultadoSelect.toString();
    }

    public static String valorStockTotal() {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Valor total de todos productos del supermercado: "));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT SUM(precio*stock_actual) AS valorStock FROM producto")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(String.format("%,.2f€", rs.getDouble("valorStock")));
                resultadoSelect.append("\n");
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se puede obtener el valor del supermercado.")).append("\n");
        }
        return resultadoSelect.toString();
    }

    public static String valorStockSeccion(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Valor de los productos de la sección [%s]:", Supermercado.descripcionSeccion(id_seccion)));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT SUM(precio*stock_actual) AS valorStockSeccion FROM producto WHERE id_seccion=?")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            if (Supermercado.descripcionSeccion(id_seccion).isEmpty()) { // Verifica que la sección existe
                resultadoSelect.append(Color.rojo("ERROR: La sección no existe."));
            } else {
                while (rs.next()) {
                    resultadoSelect.append(String.format("%,.2f€", rs.getDouble("valorStockSeccion")));
                    resultadoSelect.append("\n");
                }
            }
        } catch (SQLException ex) {
            resultadoSelect.append(Color.rojo("ERROR: No se puede obtener el valor de la sección.\n"));
        }
        return resultadoSelect.toString();
    }

    public static String productosDeSección(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Lista de productos de la sección [%s]\n", Supermercado.descripcionSeccion(id_seccion)));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT * FROM producto WHERE UPPER(id_seccion) LIKE ? ORDER BY descripcion")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) { // Verifica si hay al menos una fila en el resultset
                resultadoSelect.append(Color.rojo("No hay productos en esta sección o la sección no existe."));
            } else {
                resultadoSelect.append(String.format("%-40s %-6s %-5s\n", "DESCRIPCIÓN", "PRECIO", "STOCK"));

                // Filas de productos con formato de ancho fijo
                while (rs.next()) {
                    resultadoSelect.append(String.format("%-40s %6.2f %5d\n",
                            rs.getString("descripcion"),
                            rs.getFloat("precio"),
                            rs.getInt("stock_actual")));
                }
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se pueden listar los productos de la sección %s.", id_seccion)).append("\n");
        }
        return resultadoSelect.toString();
    }

    public static String descripcionSeccion(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT descripcion FROM seccion WHERE id_seccion = ?")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se pueden listar los productos de la sección %s.", id_seccion)).append("\n");
        }
        return resultadoSelect.toString();
    }

    public static String actualizarPrecioSeccion(String id_seccion, double porcentaje) {
        StringBuilder resultado = new StringBuilder();

        String sentenciaSQL = "UPDATE producto SET precio = precio + (precio * ? / 100) WHERE id_seccion = ?";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            st.setDouble(1, porcentaje);
            st.setString(2, id_seccion);
            int filasAfectadas = st.executeUpdate();

            resultado.append(String.format("Actualizando los precios de %d productos en la sección %s...", filasAfectadas, Supermercado.descripcionSeccion(id_seccion)));
            if (filasAfectadas > 0) {
                resultado.append(Color.verde("OK"));
            } else {
                resultado.append(Color.rojo("La sección no tiene productos."));
            }
        } catch (SQLException e) {
            resultado.append(Color.rojo(String.format("ERROR: Falló la actualización de los precios en la sección %s: %s%n", id_seccion, e.getMessage())));
        }
        return resultado.toString();
    }

    /* NUEVOS MÉTODOS PARA EMPLEADOS */
    /**
     * Muestra todos los empleados ordenados según el campo indicado.
     *
     * @param orderBy El campo por el que se ordenan (nombre, id_empleado o
     * id_seccion).
     * @return Un String con la lista de empleados ordenada o un mensaje de
     * error.
     */
    public static String selectAllEmpleadosOrderBy(String orderBy) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(String.format("Empleados ordenados por %s%n", orderBy));

        String formatoCabecera = "%-6s %-30s %10s %-10s%n";
        String formatoFila = "%-6s %-30s %10d %-10s%n";

        resultado.append(Color.azul(String.format(formatoCabecera, "CÓDIGO", "NOMBRE", "SALARIO", "SECCIÓN")));

        String sentenciaSQL = "SELECT * FROM empleado ORDER BY " + orderBy + " ASC";
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultado.append(String.format(formatoFila,
                        rs.getString("id_empleado"),
                        rs.getString("nombre"),
                        rs.getInt("salario_anual"),
                        rs.getString("id_seccion")));
            }
        } catch (SQLException ex) {
            resultado.append(Color.rojo(String.format("ERROR al ordenar por %s.%n", orderBy)));
        }
        return resultado.toString();
    }

    /**
     * Lista los empleados que trabajan en una sección concreta.
     *
     * @param id_seccion El identificador de la sección.
     * @return Un String con la lista de empleados o un mensaje de error si
     * ocurre un fallo.
     */
    public static String empleadosDeSeccion(String id_seccion) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(String.format("Lista de empleados de la sección [%s]%n", descripcionSeccion(id_seccion)));

        String sentenciaSQL = "SELECT * FROM empleado WHERE UPPER(id_seccion) = ? ORDER BY nombre";
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            st.setString(1, id_seccion.toUpperCase());
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) {
                resultado.append(Color.rojo("No hay empleados en esta sección o la sección no existe."));
            } else {
                String formatoCabecera = "%-6s %-30s %10s %-10s%n";
                String formatoFila = "%-6s %-30s %10d %-10s%n";
                resultado.append(String.format(formatoCabecera, "CÓDIGO", "NOMBRE", "SALARIO", "SECCIÓN"));
                while (rs.next()) {
                    resultado.append(String.format(formatoFila,
                            rs.getString("id_empleado"),
                            rs.getString("nombre"),
                            rs.getInt("salario_anual"),
                            rs.getString("id_seccion")));
                }
            }
        } catch (SQLException ex) {
            resultado.append(Color.rojo(String.format("ERROR: no se pueden listar los empleados de la sección %s.%n", id_seccion)));
        }
        return resultado.toString();
    }

    /**
     * Incrementa el salario anual de todos los empleados de una sección según
     * el porcentaje indicado.
     *
     * @param id_seccion El identificador de la sección.
     * @param porcentaje El porcentaje de incremento.
     * @return Un String con el resultado de la operación.
     */
    public static String aumentarSalarioSeccion(String id_seccion, double porcentaje) {
        StringBuilder resultado = new StringBuilder();

        String sentenciaSQL = "UPDATE empleado SET salario_anual = salario_anual + (salario_anual * ? / 100) WHERE id_seccion = ?";
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            st.setDouble(1, porcentaje);
            st.setString(2, id_seccion);
            int filasAfectadas = st.executeUpdate();

            resultado.append(String.format("Actualizando el salario de %d empleados en la sección [%s]...",
                    filasAfectadas, descripcionSeccion(id_seccion)));
            if (filasAfectadas > 0) {
                resultado.append(Color.verde(" OK"));
            } else {
                resultado.append(Color.rojo(" La sección no tiene empleados o no existe."));
            }
        } catch (SQLException e) {
            resultado.append(Color.rojo(String.format("ERROR: Falló la actualización de salarios en la sección %s: %s%n", id_seccion, e.getMessage())));
        }
        return resultado.toString();
    }
}
