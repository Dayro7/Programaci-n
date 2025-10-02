package tarea02;

import java.util.Scanner;

/**
 * Ejercicio 1. SUELDOS DE EMPLEADOS.
 *
 * @author Morales Cruz, Dayro
 */
public class Ejercicio01 {

    public static void main(String[] args) {
        //----------------------------------------------
        //          Declaraci�n de variables 
        //----------------------------------------------
        // Ajustamos el sueldo base para un salario anual de 21,280.00?
        final double SUELDO_BASE = 1500.00; // Ajustado para que el sueldo anual sea 21,280?
        final double EXTRA_MAS_DE_CINCO_ANOS = 100.0;
        final double EXTRA_MENOS_DE_CINCO_ANOS = 20.0;
        final double EXTRA_POR_ANIO = 50.0;
        final int MESES_EN_UN_ANO = 14;

        // Variables de entrada
        int opcion;
        int antiguedad;

        // Clase Scanner para petici�n de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("Ejercicio 2: Sueldos de Empleados\n");
        System.out.println("-----------------------------------------");

        do {
            // 1: Sacamos por pantalla el men� de opciones y pedimos que introduzca una.
            System.out.println("Men� de opciones:");
            System.out.println("1. Calcular sueldo en funci�n de la antig�edad");
            System.out.println("2. Calcular el coste total en sueldos para la empresa");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opci�n: ");
            opcion = teclado.nextInt();
            System.out.println(); // Espacio en blanco

            //----------------------------------------------
            //                 Procesamiento 
            //----------------------------------------------
            // Cuando haya introducido una opci�n v�lida, llevamos a cabo la acci�n oportuna
            switch (opcion) {
                case 1 -> {
                    // Calcular el sueldo de tres empleados
                    double[] sueldos = new double[3]; // Array para guardar los sueldos de los empleados
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Introduce la antig�edad del empleado " + (i + 1) + ": ");
                        antiguedad = teclado.nextInt();
                        sueldos[i] = SUELDO_BASE; // Sueldo base para el empleado

                        // Calcular el extra seg�n la antig�edad
                        if (antiguedad > 5) {
                            sueldos[i] += EXTRA_MAS_DE_CINCO_ANOS;
                        } else {
                            sueldos[i] += EXTRA_MENOS_DE_CINCO_ANOS;
                        }
                        sueldos[i] += antiguedad * EXTRA_POR_ANIO; // Sumar el extra por antig�edad
                    }

                    // Mostrar los sueldos
                    System.out.println("\nLos salarios mensuales de los empleados son (sin prorrateo de pagas extra):");
                    for (int i = 0; i < 3; i++) {
                        System.out.printf("Empleado %d: %.1f euros.%n", (i + 1), sueldos[i]);
                    }
                    System.out.println(); // Espacio en blanco
                }

                case 2 -> {
                    // Calcular el coste de un nuevo empleado
                    double sueldoNuevoEmpleado = SUELDO_BASE; // Sueldo base sin antig�edad

                    // Calcular coste total anual y mensual
                    double costeAnual = (sueldoNuevoEmpleado + EXTRA_MENOS_DE_CINCO_ANOS) * MESES_EN_UN_ANO; // 14 meses
                    double costeMensual = costeAnual / 12; // Coste mensual (prorrateado)

                    // Mostrar resultados
                    System.out.printf("El salario anual para un empleado sin antig�edad es: %.2f euros%n", costeAnual);
                    System.out.printf("El salario mensual para un empleado sin antig�edad (con pagas extra prorrateadas) es: %.2f euros%n", costeMensual);
                    System.out.println(); // Espacio en blanco
                }

                case 3 ->
                    System.out.println("Saliendo del programa...");

                default ->
                    System.out.println("Opci�n inv�lida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 3);

        teclado.close();
    }
}
