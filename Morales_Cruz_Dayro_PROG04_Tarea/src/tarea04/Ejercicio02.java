package tarea04;

import java.util.Scanner;

/**
 * Ejercicio 2. Rotar matrices cuadradas.
 * @author Dayro Morales Cruz
 */
public class Ejercicio02 {

    public static void main(String[] args) {
        
        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes
        final String REGEX_VALIDO = "^[a-zA-Z0-9]+$"; // Valida solo letras y números (sin espacios ni caracteres especiales)
        
        // Variables de entrada y salida
        String[][] matrizOriginal; // Matriz inicial ingresada por el usuario
        String[][] matrizRotada;   // Matriz rotada 90 grados
        
        // Variables auxiliares
        int tamanioMatriz; // Tamaño de la matriz (cuadrada)
        boolean datosValidos = true; // Para verificar validez general de datos
        
        // Clase Scanner para leer datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //      Entrada de datos y validación
        //----------------------------------------------
        System.out.println("Ejercicio 2. Rotar matrices cuadradas.");
        System.out.println("--------------------------------------");

        System.out.print("Introduce la primera fila de valores separados por comas: ");
        String[] filaInicial = teclado.nextLine().trim().split(",");
        tamanioMatriz = filaInicial.length; // Calcula el tamaño de la matriz

        if (tamanioMatriz <= 1) {
            System.out.println("Error: La matriz no puede ser de tamaño 0x0 ni 1x1.");
            return;
        }

        System.out.println("Info: Vamos a trabajar con una matriz de " + tamanioMatriz + "x" + tamanioMatriz);

        matrizOriginal = new String[tamanioMatriz][tamanioMatriz]; // Inicializa la matriz cuadrada

        // Validar y procesar la primera fila
        for (int i = 0; i < tamanioMatriz; i++) {
            if (!filaInicial[i].trim().matches(REGEX_VALIDO)) {
                datosValidos = false;
                break;
            }
            matrizOriginal[0][i] = filaInicial[i].trim();
        }

        if (!datosValidos) {
            System.out.println("Error: La fila contiene valores no permitidos.");
            return;
        }

        // Solicitar y validar las filas restantes
        for (int fila = 1; fila < tamanioMatriz; fila++) {
            System.out.print("Introduce la fila " + (fila + 1) + " de valores separados por comas: ");
            String[] valoresFila = teclado.nextLine().trim().split(",");

            if (valoresFila.length != tamanioMatriz) {
                System.out.println("Error: El número de elementos de la fila debe ser " + tamanioMatriz);
                return;
            }

            for (int i = 0; i < tamanioMatriz; i++) {
                if (!valoresFila[i].trim().matches(REGEX_VALIDO)) {
                    System.out.println("Error: La fila " + (fila + 1) + " tiene valores no permitidos.");
                    return;
                }
                matrizOriginal[fila][i] = valoresFila[i].trim();
            }
        }

        //----------------------------------------------
        //              Procesamiento
        //----------------------------------------------
        matrizRotada = new String[tamanioMatriz][tamanioMatriz];
        for (int i = 0; i < tamanioMatriz; i++) {
            for (int j = 0; j < tamanioMatriz; j++) {
                matrizRotada[j][tamanioMatriz - 1 - i] = matrizOriginal[i][j]; // Rota 90 grados
            }
        }

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println("Matriz original:");
        for (int i = 0; i < tamanioMatriz; i++) {
            for (int j = 0; j < tamanioMatriz; j++) {
                System.out.print(matrizOriginal[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Matriz rotada 90 grados a la derecha:");
        for (int i = 0; i < tamanioMatriz; i++) {
            for (int j = 0; j < tamanioMatriz; j++) {
                System.out.print(matrizRotada[i][j] + " ");
            }
            System.out.println();
        }
    }
}
