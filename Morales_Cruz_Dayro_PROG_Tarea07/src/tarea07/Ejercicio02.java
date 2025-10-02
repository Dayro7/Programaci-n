package tarea07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.text.Collator;

/**
 * Ejercicio 2. Trabajando con listas de módulos
 *
 * @author Morales_Cruz_Dayro
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        // Variables de entrada
        String[] modulosDAW;
        String[] modulosDAM;

        // Variables auxiliares
        List<String> listaDAW;
        List<String> listaDAM;
        List<String> modulosMatriculados;
        Set<String> conjuntoMatriculados;

        // Variables de salida
        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        // No hay entrada de datos, pues se usa un número fijo de elementos
        System.out.println("LISTAS DE MÓDULOS");
        System.out.println("-----------------------------");

        // Inicializamos los arrays que vamos a utilizar
        modulosDAW = Utilidades.getArrayModulosDAW();
        modulosDAM = Utilidades.getArrayModulosDAM();

        // Instanciamos las colecciones para trabajar con ellas
        listaDAW = new ArrayList<>(List.of(modulosDAW));
        listaDAM = new ArrayList<>(List.of(modulosDAM));
        modulosMatriculados = new ArrayList<>();
        conjuntoMatriculados = new HashSet<>();

        //----------------------------------------------
        //               Procesamiento
        //----------------------------------------------
        // Mostrar contenido inicial de las listas
        System.out.printf("Contenido inicial de la lista de módulos de DAW:\n");
        Utilidades.mostrarModulos(listaDAW);

        System.out.println();
        System.out.printf("Contenido inicial de la lista de módulos de DAM:\n");
        Utilidades.mostrarModulos(listaDAM);

        // Inspeccionamos la lista de módulos de DAW
        for (int i = 0; i < listaDAW.size(); i++) {
            String modulo = listaDAW.get(i);
            if (!modulo.contains("i")) {
                // Agregar a la lista y al conjunto de matriculados
                modulosMatriculados.add(modulo);
                conjuntoMatriculados.add(modulo);
                // Marcar el módulo en la lista de DAW
                listaDAW.set(i, "**" + modulo + "**");
            }
        }

        // Ordenar lista de DAM por nombre y mostrar
        listaDAM.sort(new ComparadorModuloPorNombre());
        System.out.println();
        System.out.printf("Ordenación de la lista de módulos de DAM por nombre (alfabético):\n");
        Utilidades.mostrarModulos(listaDAM);

        // Ordenar lista de DAM por código y mostrar
        listaDAM.sort(new ComparadorModuloPorCodigo());
        System.out.println();
        System.out.printf("Ordenación de la lista de módulos de DAM por código:\n");
        Utilidades.mostrarModulos(listaDAM);

        //----------------------------------------------
        //            Salida de resultados
        //----------------------------------------------
        // Mostrar contenido final de las listas
        System.out.println();
        System.out.printf("Contenido final de la lista de módulos de DAW:\n");
        Utilidades.mostrarModulos(listaDAW);

        System.out.println();
        System.out.printf("Contenido final de la lista de módulos matriculados (DAW):\n");
        Utilidades.mostrarModulos(modulosMatriculados);

        System.out.println();
        System.out.printf("Contenido final del conjunto de módulos matriculados (DAW):\n");
        Utilidades.mostrarModulos(conjuntoMatriculados);
    }
}

/**
 * Clase que permite comparar dos módulos usando como criterio de comparación su
 * nombre. Se trata de una comparación alfabética.
 *
 * @author Morales_Cruz_Dayro
 */
class ComparadorModuloPorNombre implements Comparator<String> {

    private Collator collator;

    public ComparadorModuloPorNombre() {
        collator = Collator.getInstance(new Locale("es", "ES"));
        collator.setStrength(Collator.PRIMARY);
    }

    @Override
    public int compare(String m1, String m2) {
        String nombre1 = m1.substring(m1.indexOf('-') + 1).trim();
        String nombre2 = m2.substring(m2.indexOf('-') + 1).trim();
        return collator.compare(nombre1, nombre2);
    }
}

/**
 * Clase que permite comparar dos módulos usando como criterio de comparación su
 * código.
 *
 * @author Morales_Cruz_Dayro
 */
class ComparadorModuloPorCodigo implements Comparator<String> {

    @Override
    public int compare(String m1, String m2) {
        return m1.substring(0, m1.indexOf('-')).compareTo(m2.substring(0, m2.indexOf('-')));
    }
}
