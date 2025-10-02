package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 1: Lectura/escritura de una biblioteca de libros en ficheros de
 * texto.
 *
 * @author Morales Cruz Dayro
 */
public class Ejercicio1 {

    /**
     * Método principal.
     *
     * @param args argumentos que recibe el método
     */
    public static void main(String args[]) {

        //----------------------------------------------
        //          Declaracion de variables 
        //----------------------------------------------
        // Constantes
        String rutaListadoLibros = System.getProperty("user.dir") + "/recursos/ListadoLibros.txt";
        String rutaBiblioteca = System.getProperty("user.dir") + "/recursos/Biblioteca.txt";

        // Variables de entrada
        Biblioteca biblioteca = new Biblioteca();
        List<Libro> listaLibros = new ArrayList<>();

        //----------------------------------------------
        //       Entrada de datos + Procesamiento
        //----------------------------------------------
        // Abrimos archivo de libros ListadoLibros.txt
        System.out.println("Abriendo archivo de libros...");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaListadoLibros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Dividir la línea por ";"
                String[] partes = linea.split(";");
                if (partes.length < 5) {
                    System.out.println("Línea con formato incorrecto: " + linea);
                    continue;
                }

                // Extraer datos
                String titulo = partes[0].trim();
                String autor = partes[1].trim();
                LocalDate fechaCreacion = LocalDate.parse(partes[2].trim());
                String genero = partes[4].trim();

                // Extraer capítulos
                List<String> capitulos = new ArrayList<>();
                String[] capArray = partes[3].split(",");
                for (String cap : capArray) {
                    capitulos.add(cap.trim());
                }

                // Crear objeto Libro y añadirlo a la biblioteca
                Libro libro = new Libro(titulo, autor, fechaCreacion, capitulos, genero);
                biblioteca.add(libro);
                listaLibros.add(libro);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de libros: " + e.getMessage());
        }

        System.out.println("Cerrando archivo de libros...");
        System.out.println();

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        // Abrimos el archivo de la Biblioteca de libros Biblioteca.txt
        System.out.println("Abriendo archivo de la biblioteca...");

        try (PrintWriter pw = new PrintWriter(rutaBiblioteca)) {
            pw.println("**********************************************************************************************************************************");
            pw.println("LIBRO DE LIBROS");
            pw.println("**********************************************************************************************************************************");

            for (Libro libro : listaLibros) {
                pw.println("TITULO DEL LIBRO:" + libro.getTitulo());
                pw.println("AUTOR:" + libro.getAutor());
                pw.println("FECHA DE CREACIÓN:" + libro.getFechaCreacion());
                pw.println("GENERO:" + libro.getGenero());
                pw.println("CAPITULOS:" + formatearCapitulos(libro.getCapitulos()));
                pw.println("**********************************************************************************************************************************");
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo de la biblioteca: " + e.getMessage());
        }

        System.out.println("Cerrando archivo de la biblioteca...");
        System.out.println();
        System.out.println("Archivos cerrados y procesamiento finalizado");
        System.out.println("---------");
        System.out.println();
        System.out.println("Fin del programa.");
    }

    /**
     * Método auxiliar para formatear la lista de capítulos en el formato
     * requerido.
     */
    private static String formatearCapitulos(List<String> capitulos) {
        return "[" + String.join(", ", capitulos) + ".]";
    }
}
