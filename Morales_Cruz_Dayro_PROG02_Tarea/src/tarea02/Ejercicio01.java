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
        //          Declaración de variables 
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

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("Ejercicio 2: Sueldos de Empleados\n");
        System.out.println("-----------------------------------------");

        do {
            // 1: Sacamos por pantalla el menú de opciones y pedimos que introduzca una.
            System.out.println("Menú de opciones:");
            System.out.println("1. Calcular sueldo en función de la antigüedad");
            System.out.println("2. Calcular el coste total en sueldos para la empresa");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            System.out.println(); // Espacio en blanco

            //----------------------------------------------
            //                 Procesamiento 
            //----------------------------------------------
            // Cuando haya introducido una opción válida, llevamos a cabo la acción oportuna
            switch (opcion) {
                case 1 -> {
                    // Calcular el sueldo de tres empleados
                    double[] sueldos = new double[3]; // Array para guardar los sueldos de los empleados
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Introduce la antigüedad del empleado " + (i + 1) + ": ");
                        antiguedad = teclado.nextInt();
                        sueldos[i] = SUELDO_BASE; // Sueldo base para el empleado

                        // Calcular el extra según la antigüedad
                        if (antiguedad > 5) {
                            sueldos[i] += EXTRA_MAS_DE_CINCO_ANOS;
                        } else {
                            sueldos[i] += EXTRA_MENOS_DE_CINCO_ANOS;
                        }
                        sueldos[i] += antiguedad * EXTRA_POR_ANIO; // Sumar el extra por antigüedad
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
                    double sueldoNuevoEmpleado = SUELDO_BASE; // Sueldo base sin antigüedad

                    // Calcular coste total anual y mensual
                    double costeAnual = (sueldoNuevoEmpleado + EXTRA_MENOS_DE_CINCO_ANOS) * MESES_EN_UN_ANO; // 14 meses
                    double costeMensual = costeAnual / 12; // Coste mensual (prorrateado)

                    // Mostrar resultados
                    System.out.printf("El salario anual para un empleado sin antigüedad es: %.2f euros%n", costeAnual);
                    System.out.printf("El salario mensual para un empleado sin antigüedad (con pagas extra prorrateadas) es: %.2f euros%n", costeMensual);
                    System.out.println(); // Espacio en blanco
                }

                case 3 ->
                    System.out.println("Saliendo del programa...");

                default ->
                    System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
            }
        } while (opcion != 3);

        teclado.close();
    }
}
