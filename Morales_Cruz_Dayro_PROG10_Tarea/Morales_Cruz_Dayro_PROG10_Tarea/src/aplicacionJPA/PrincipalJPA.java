package aplicacionJPA;

import H2.H2JDBC;
import util.Color;
import H2.H2ServerManager;
import JPA.AdministradorDePersistencia;
import java.util.Scanner;

/**
 * Clase principal de la aplicación de gestión de supermercado usando JPA.
 *
 * <p>
 * Presenta un menú en consola que permite realizar operaciones CRUD sobre las
 * entidades Sección, Producto y Empleado. Además, permite abrir la consola web
 * de H2 y también carga un script SQL inicial.</p>
 *
 * <p>
 * Requiere que el servidor H2 esté iniciado correctamente y que se cree la
 * EntityManagerFactory antes de mostrar el menú.</p>
 *
 * @author Dayro Morales Cruz
 * @version abril/2025
 */
public class PrincipalJPA {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;

        if (H2ServerManager.iniciarServidorH2()) { // Inicia el servidor antes de usar JPA

            if (AdministradorDePersistencia.creaEntityManagerFactory()) {

                // Crea un conjunto de datos de prueba 
                H2JDBC.execScriptSQL("datos.sql");

                do {
                    System.out.println();
                    System.out.println(Color.azul("===================================================================="));
                    System.out.println(Color.azul("======== Gestión de Supermercado 1.0 (usando tecnología JPA)========"));
                    System.out.println(Color.azul("===================================================================="));
                    System.out.println(Color.azul("[SECCIONES]"));
                    System.out.println("   [IS]Insertar [LS]Listar [MS]Modificar [ES]Eliminar");
                    System.out.println(Color.azul("[PRODUCTOS]"));
                    System.out.println("   [IP]Insertar [CP]Consultar [LP] Listar [MP]Modificar [EP]Eliminar");
                    System.out.println(Color.azul("[EMPLEADOS]"));
                    System.out.println("   [IE]Insertar [CE]Consultar [LE] Listar [ME]Modificar [EE]Eliminar");
                    System.out.println(Color.azul("[GENERAL]"));
                    System.out.println("   [AC]Abrir consola H2 en navegador web");
                    System.out.println("   [S]Salir");
                    System.out.println(Color.azul("===================================================================="));
                    System.out.println("Opción:");

                    opcion = teclado.nextLine().toUpperCase();

                    switch (opcion) {
                        case "IS" ->
                            GestorEntidadesJPA.insertarSeccion();
                        case "LS" ->
                            GestorEntidadesJPA.consultarSecciones();
                        case "MS" ->
                            GestorEntidadesJPA.modificarSeccion();
                        case "ES" ->
                            GestorEntidadesJPA.eliminarSeccion();
                        case "IP" ->
                            GestorEntidadesJPA.insertarProducto();
                        case "CP" ->
                            GestorEntidadesJPA.consultarProducto();
                        case "LP" ->
                            GestorEntidadesJPA.listarProductos();
                        case "MP" ->
                            GestorEntidadesJPA.modificarProducto();
                        case "EP" ->
                            GestorEntidadesJPA.eliminarProducto();
                        case "IE" ->
                            GestorEntidadesJPA.insertarEmpleado();
                        case "CE" ->
                            GestorEntidadesJPA.consultarEmpleado();
                        case "LE" ->
                            GestorEntidadesJPA.listarEmpleados();
                        case "ME" ->
                            GestorEntidadesJPA.modificarEmpleado();
                        case "EE" ->
                            GestorEntidadesJPA.eliminarEmpleado();
                        case "AC" ->
                            H2ServerManager.abrirConsolaH2EnNavegador();
                        case "S" ->
                            System.out.println("\nSaliendo de la aplicación... ");
                        default ->
                            System.out.println("Opción no válida.");
                    }
                } while (!opcion.equals("S"));
            }
        }
        AdministradorDePersistencia.cerrarEntityManagerFactory();
        H2ServerManager.detenerServidorH2();
    }
}
