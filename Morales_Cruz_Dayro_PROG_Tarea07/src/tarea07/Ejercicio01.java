package tarea07;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * Ejercicio 1. Creando conjuntos de ciclos DAW y DAM con sus módulos
 * @author Morales_Cruz_Dayro
 */
public class Ejercicio01 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables
        //----------------------------------------------

        // Constantes

        // Variables de entrada

        // Variables auxiliares

        // Variables de salida

        //----------------------------------------------
        //                Entrada de datos
        //----------------------------------------------

        // No hay entrada de datos, pues se usa un número fijo de elementos

        System.out.println("CONJUNTOS DE MÓDULOS DE DAW Y DAM");
        System.out.println("--------------------------------");

        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------

        // Inicializamos los arrays que vamos a utilizar
        String[] modulosDAW = Utilidades.getArrayModulosDAW();
        String[] modulosDAM = Utilidades.getArrayModulosDAM();

        // Inicializando las colecciones que vamos a utilizar
        // Al usar conjuntos (Set) garantizamos que no se pueden repetir elementos
        Set<String> conjuntoDAW = new HashSet<>(Arrays.asList(modulosDAW));
        Set<String> conjuntoDAM = new HashSet<>(Arrays.asList(modulosDAM));

        // Unión de los dos conjuntos
        Set<String> union = new HashSet<>(conjuntoDAW);
        union.addAll(conjuntoDAM);

        // Intersección de los conjuntos
        Set<String> interseccion = new HashSet<>(conjuntoDAW);
        interseccion.retainAll(conjuntoDAM);

        // Diferencia de los conjuntos
        Set<String> diferencia = new HashSet<>(conjuntoDAM);
        diferencia.removeAll(conjuntoDAW);

        //----------------------------------------------
        //              Salida de Resultados
        //----------------------------------------------

        // Recorremos los conjuntos y mostramos su contenido por pantalla
        System.out.printf("Conjunto C1 (módulos DAW):\n");
        Utilidades.mostrarModulos(conjuntoDAW);

        System.out.printf("\nConjunto C2 (módulos DAM):\n");
        Utilidades.mostrarModulos(conjuntoDAM);

        System.out.printf("\nUnión C1 y C2:\n");
        Utilidades.mostrarModulos(union);

        System.out.printf("\nInterseccion C1 y C2:\n");
        Utilidades.mostrarModulos(interseccion);

        System.out.printf("\nDiferencia C1 y C2:\n");
        Utilidades.mostrarModulos(diferencia);
    }
}
