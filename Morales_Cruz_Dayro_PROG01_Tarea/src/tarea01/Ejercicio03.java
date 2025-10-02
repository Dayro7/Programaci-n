package tarea01;

import java.util.Scanner;

/**
 * Ejercicio 3. Validación de contraseñas.
 *
 * @author Indica el nombre del alumno/a
 */
public class Ejercicio03 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Variables de entrada
        String password;

        // Variables auxiliares
        boolean esValida;
        char primerCaracter, ultimoCaracter;

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("VALIDACIÓN DE CONTRASEÑAS");
        System.out.println("-------------------------");
        System.out.print("Introduce la contraseña: ");
        password = teclado.nextLine();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        // Comprobamos si tiene como mínimo 12 caracteres
        esValida = password.length() >= 12;

        // Comprobamos si comienza por una vocal (mayúscula o minúscula)
        primerCaracter = password.charAt(0);
        esValida = esValida && "AEIOUaeiou".indexOf(primerCaracter) != -1;

        // Comprobamos si termina con una consonante (mayúscula o minúscula)
        ultimoCaracter = password.charAt(password.length() - 1);
        esValida = esValida && "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(ultimoCaracter) != -1;

        // Comprobamos si contiene al menos uno de los caracteres especiales requeridos
        esValida = esValida && (password.contains("@") || password.contains("#") || password.contains("_")
                || password.contains(".") || password.contains(";"));

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println(esValida ? "Contraseña es VALIDA" : "Contraseña es NO VALIDA");
    }
}
