package tarea01;

import java.util.Scanner;

/**
 * Ejercicio 1. Cálculos aritméticos.
 *
 * @author Morales Cruz, Dayro
 */
public class Ejercicio01 {

    // Definición del enum para las operaciones
    enum Operaciones {
        OPERACION_1,
        OPERACION_2,
        OPERACION_3
    }

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Variables de entrada
        double x, y, z;

        // Variables de salida
        double expre1, expre2, expre3;

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("CÁLCULOS ARITMÉTICOS");
        System.out.println("--------------------");

        // Solicitar los valores de x, y y z
        System.out.print("Introduce el valor de x: ");
        x = teclado.nextDouble();

        System.out.print("Introduce el valor de y: ");
        y = teclado.nextDouble();

        System.out.print("Introduce el valor de z: ");
        z = teclado.nextDouble();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        // Primera expresión: x / 3 + 8
        expre1 = x / 3.0 + 8.0;

        // Segunda expresión: (x^2 / y^2) + (y^2 / z^2)        
        expre2 = (x * x) / (y * y) + (y * y) / (z * z);

        // Tercera expresión: (x^2 + 3xy + y^2) / (1 / x^2)
        expre3 = (x * x + 3 * x * y + y * y) / (1 / (x * x));

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");

        // Mostrar resultados con los valores del enumerado
        System.out.println(Operaciones.OPERACION_1 + ": " + expre1);
        System.out.println(Operaciones.OPERACION_2 + ": " + expre2);
        System.out.println(Operaciones.OPERACION_3 + ": " + expre3);
    }
}
