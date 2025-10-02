package tarea01;

/**
 * Ejercicio 2. Operaciones con constantes y variables.
 *
 * @author Morales Cruz, Dayro
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //---------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        final double CONSTANTE1 = 80.3;
        final int CONSTANTE2 = 3;

        // Variables de entrada

        // Variables de salida

        // Variables auxiliares
        boolean valorBool;
        byte enteroByte;
        short enteroShort;
        int enteroInt, producto;
        long enteroLong;
        double decimalDoble;
        float decimalSimple;

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        // No hay entrada de datos
        System.out.println("OPERACIONES CON CONSTANTES Y VARIABLES.");
        System.out.println("---------------------------------------");
        System.out.println(" ");

        //----------------------------------------------
        //     Procesamiento 
        //----------------------------------------------
        
        //----------------------------------------------

        // Los booleanos ni puede almacenar números, solo True o False.
        // valorBool = 0;  // ERROR: No se puede asignar un número a un booleano.

        // decimalSimple = 9.9 * 4.6;       
        // Para evitar el error de precisión, hay que hacer casting a float.
        decimalSimple = (float) (9.9 * 4.6);  // Casting explícito de double a float.

        // Conversión implícita: el valor de 'a' (char) se convierte automáticamente a su valor numérico en ASCII.
        enteroInt = 'a';  // CORRECTO: 'a' se convierte a 97 que es su valor en ASCII.

        // decimalDoble = 5,17;  // ERROR: El separador decimal en Java es el punto, no la coma.
        decimalDoble = 5.17;  // CORRECTO: Usar el punto decimal.

        enteroLong = 25_369L;  // CORRECTO: El guion bajo mejora la lectura del codigo y no afecta el valor.

        // Como el resultado de multiplicar un long por un int puede ser más grande que int, necesitamos hacer casting.
        producto = (int) (enteroLong * enteroInt);  // Casting explícito de long a int.

        // valorBool = (97 == 'a' == 97);  // ERROR: No se pueden hacer comparaciones booleanas en cadena.
        valorBool = (97 == 'a');  // CORRECTO: Comparar si 97 es igual al valor numérico de 'a' (97).

        // CONSTANTE1 = 100.3;  // ERROR: No se puede cambiar el valor de una constante final.

        // Conversión implícita: 'c' (char) se convierte a un valor numérico antes de ser asignado.
        decimalSimple = 'c';  // CORRECTO: 'c' se convierte a su valor numérico ASCII, luego a float.

        decimalDoble = 3.2 * 4.7;  // CORRECTO: Multiplicación entre doubles, resultado en double.

        // producto = CONSTANTE1 * CONSTANTE2;  // ERROR: El resultado es double, pero producto es int.
        producto = (int) (CONSTANTE1 * CONSTANTE2);  // Casting explícito de double a int.

        // Conversión implícita: el float se convierte automáticamente a double.
        decimalDoble = 5.67F;  // CORRECTO: Conversión implícita de float a double.

        // Conversión implícita: un int se convierte automáticamente a double.
        decimalDoble = 8;  // CORRECTO: Conversión de int a double.

        // División entera: el resultado de 1/2 es 0, ya que se descarta la parte decimal.
        enteroInt = 1 / 2;  // CORRECTO: División entre enteros da como resultado 0.
    }
}

/* 
Explicación de los errores:
- Booleanos y constantes:
  valorBool = 0;: No se puede asignar un número a un booleano, solo true o false.
  CONSTANTE1 = 100.3;: Las constantes no pueden cambiar su valor después de ser inicializadas.

- Separador decimal:
  decimalDoble = 5,17;: En Java, el punto se utiliza para separar decimales, no la coma.

- Casting explícito:
  Cuando el tipo de dato de destino es más pequeño o tiene menos precisión que el tipo de dato del valor a asignar, 
  es necesario un casting explícito (por ejemplo, de long a int o de double a float).

- Conversiones implícitas:
  Java realiza conversiones implícitas automáticamente cuando se asigna un valor de menor precisión a uno de mayor precisión,
  como cuando se asigna un char a un int, o un int a un double.
*/
