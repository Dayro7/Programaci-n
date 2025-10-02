// Ejercicio 1: Comprimir cadenas
package tarea04;

import java.util.Scanner;

/**
 * Ejercicio 1. Comprimir cadenas.
 * @author Dayro Morales Cruz
 */
public class Ejercicio01 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes
        final String REGEX_VALIDO = "^[a-zA-Z]+$"; // Expresión regular: solo permite letras sin espacios ni 'ñ'
        
        // Variables de entrada
        String cadenaOriginal; // Almacena la cadena ingresada por el usuario
        
        // Variables de salida
        String cadenaComprimida = ""; // Resultado de la compresión
        
        // Variables auxiliares
        char caracterActual; // Carácter en proceso
        int contador = 1; // Contador de repeticiones consecutivas del carácter actual
        
        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("Ejercicio 1. Comprimir cadenas.");
        System.out.println("-------------------------------");
        System.out.print("Escribe una cadena de texto: ");
        cadenaOriginal = teclado.nextLine(); // Captura la cadena introducida por el usuario

        //----------------------------------------------
        //        Validación de entrada 
        //----------------------------------------------
        if (!cadenaOriginal.matches(REGEX_VALIDO)) { // Valida que la cadena contenga solo letras
            System.out.println();
            System.out.println("RESULTADO");
            System.out.println("---------");
            System.out.println("Error: Solo se permiten letras sin espacios ni caracteres especiales.");
            return; // Finaliza el programa
        }

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        for (int i = 0; i < cadenaOriginal.length(); i++) {
            caracterActual = cadenaOriginal.charAt(i); // Obtiene el carácter actual

            if (i < cadenaOriginal.length() - 1 && cadenaOriginal.charAt(i + 1) == caracterActual) {
                contador++; // Incrementa el contador si el siguiente carácter es igual
            } else {
                cadenaComprimida += caracterActual; // Añade el carácter actual al resultado
                if (contador > 1) {
                    cadenaComprimida += contador; // Añade el contador si hay repeticiones
                }
                contador = 1; // Reinicia el contador
            }
        }

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        if (cadenaComprimida.length() < cadenaOriginal.length()) {
            // Muestra la cadena comprimida solo si es más corta que la original
            System.out.println("Cadena comprimida: " + cadenaComprimida);
        } else {
            // Si no, muestra la cadena original (no tiene sentido comprimir)
            System.out.println("Cadena comprimida: " + cadenaOriginal);
        }
    }
}