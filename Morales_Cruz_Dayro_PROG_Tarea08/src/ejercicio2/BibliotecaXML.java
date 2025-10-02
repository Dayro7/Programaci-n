package ejercicio2;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que permite serializar un objeto Biblioteca al formato XML y viceversa.
 *
 * @author profe y Morales Cruz Dayro
 */
public class BibliotecaXML {

    // Ruta del archivo donde se lee y escribe el objeto Biblioteca
    private String rutaArchivo;

    // Objeto Xstream que permite la L/E con archivos XML
    private XStream xstream;

    /**
     * Metodo constructor
     *
     * @param nombreArchivo Ruta del archivo donde se lee y escribe el objeto
     * Biblioteca
     */
    public BibliotecaXML(String nombreArchivo) {
        this.rutaArchivo = nombreArchivo;
        this.xstream = new XStream();
        // Permite asignar privilegios para poder operar con los archivos XML
        this.xstream.allowTypesByWildcard(new String[]{
            "ejercicio2.**"
        });
    }

    // -----------------------------------------------------
    // Ejercicio 2: Metodos que debe implementar el alumnado
    // -----------------------------------------------------
    // 3.1. Metodo escribir()
    /**
     * Metodo que escribe, en un archivo de texto, un objeto Biblioteca
     * serializable.
     *
     * @param biblioteca Objeto Biblioteca serializable para almacenar en el
     * archivo de texto.
     */
    public void escribir(Biblioteca biblioteca) {
        // Serializamos el objeto Biblioteca a una cadena XML.
        String xml = xstream.toXML(biblioteca);

        // Escribimos la cadena en el archivo indicado por rutaArchivo.
        try (FileWriter fw = new FileWriter(rutaArchivo)) {
            fw.write(xml);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3.2. Metodo leer()
    /**
     * Metodo que lee, desde un archivo de texto, un objeto Biblioteca
     * serializado.
     *
     * @return Objeto Biblioteca que estaba almacenado en el archivo de texto.
     */
    public Biblioteca leer() {
        StringBuilder sb = new StringBuilder();
        // Leemos el contenido del archivo que contiene el XML.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Des-serializamos el contenido XML para obtener el objeto Biblioteca.

        return (Biblioteca) xstream.fromXML(sb.toString());
    }
}
