package tarea07;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Ejercicio 3. Mapa de Ciclos y sus módulos
 *
 * @author Morales_Cruz_Dayro
 */
public class Ejercicio03 {

    public static void main(String[] args) {

        //----------------------------------------------
        //    Declaración de variables y constantes
        //----------------------------------------------
        // Constantes
        String[] codigosModulosDAW = Utilidades.getArrayCodigosModulosDAW();

        // Variables de entrada
        // Variables auxiliares
        Map<Integer, List<Integer>> mapaModulos;
        List<String> listaCodigosModulosDAW;

        // Variables de salida
        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        // No se piden datos al usuario, ya que se usa un array de elementos fijos
        System.out.println("CÓDIGOS DE LOS MÓDULOS DE DAW POR CURSOS");
        System.out.println("----------------------------------------");

        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------
        // Instanciamos las variables con las que vamos a trabajar
        mapaModulos = new TreeMap<>();
        listaCodigosModulosDAW = Arrays.asList(codigosModulosDAW);

        // Recorremos la lista de códigos del ciclo de DAW (curso-codigo de modulo)
        // y creamos el mapa con cada curso y los códigos de los módulos correspondientes  
        for (String codigo : listaCodigosModulosDAW) {
            String[] partes = codigo.split("-");
            int curso = Integer.parseInt(partes[0]);
            int moduloCodigo = Integer.parseInt(partes[1]);

            // Añadimos el código del módulo al curso correspondiente en el mapa
            mapaModulos.putIfAbsent(curso, new ArrayList<>());
            mapaModulos.get(curso).add(moduloCodigo);
        }

        //----------------------------------------------
        //           Salida de resultados
        //----------------------------------------------
        System.out.printf("Contenido del mapa de códigos de módulos organizados por cursos:\n\n");
        for (Map.Entry<Integer, List<Integer>> entry : mapaModulos.entrySet()) {
            System.out.printf("Módulos de %dº curso: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
