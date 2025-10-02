package tarea06;

// ------------------------------------------------------------
//                   Clase Lancha
// ------------------------------------------------------------
/**
 * Clase que representa una lancha.
 *
 * Esta clase extiende la funcionalidad de la clase Embarcacion y añade
 * atributos y métodos específicos para las lanchas. Implementa la interfaz
 * Navegable. No se pueden crear subclases a partir de esta clase.
 *
 * @autor Morales_Cruz_Dayro
 */
public final class Lancha extends Embarcacion implements Navegable {

    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    /**
     * Número mínimo de motores permitido en las lanchas.
     */
    public static final int MIN_MOTORES = 1;

    /**
     * Número máximo de motores permitido en las lanchas.
     */
    public static final int MAX_MOTORES = 2;

    /**
     * Cantidad mínima de combustible permitido en las lanchas (en litros).
     */
    public static final int MIN_COMBUSTIBLE = 8;

    /**
     * Cantidad máxima de combustible permitido en las lanchas (en litros).
     */
    public static final int MAX_COMBUSTIBLE = 50;

    /**
     * Factor de combustible consumido por una lancha.
     */
    public static final double FACTOR_COMBUSTIBLE = 0.026;

    /**
     * Velocidad mínima permitida durante la navegación (en nudos).
     */
    public static final int MIN_VELOCIDAD_LANCHA = 1; // en nudos

    /**
     * Velocidad máxima permitida durante la navegación (en nudos).
     */
    public static final int MAX_VELOCIDAD_LANCHA = 50; // en nudos

    /**
     * Número total de lanchas creadas.
     */
    private static int numeroLanchasCreadas = 0;

    /**
     * Número de motores de la lancha.
     */
    private final int numeroMotores;

    /**
     * Cantidad de combustible en la lancha (en litros).
     */
    private double cantidadCombustible; // en litros

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor con parámetros para crear una nueva lancha.
     *
     * @param nombreBarco Nombre de la lancha.
     * @param numeroMotores Número de motores de la lancha.
     * @param cantidadCombustible Cantidad de combustible en la lancha (en
     * litros).
     * @param numeroMaximoTripulantes Número máximo de tripulantes.
     * @throws IllegalArgumentException Si el número de motores o el nivel de
     * combustible no son válidos.
     */
    public Lancha(String nombreBarco, int numeroMotores, double cantidadCombustible, int numeroMaximoTripulantes) {
        super(nombreBarco, numeroMaximoTripulantes);

        if (numeroMotores < MIN_MOTORES || numeroMotores > MAX_MOTORES) {
            throw new IllegalArgumentException("El número de motores debe estar entre " + MIN_MOTORES + " y " + MAX_MOTORES + ".");
        }
        if (cantidadCombustible < MIN_COMBUSTIBLE || cantidadCombustible > MAX_COMBUSTIBLE) {
            throw new IllegalArgumentException("El nivel de combustible debe estar entre " + MIN_COMBUSTIBLE + " y " + MAX_COMBUSTIBLE + ".");
        }

        this.numeroMotores = numeroMotores;
        this.cantidadCombustible = cantidadCombustible;
        numeroLanchasCreadas++;
    }

    /**
     * Constructor sin parámetros para crear una nueva lancha con valores por
     * defecto.
     */
    public Lancha() {
        this("Lancha " + (numeroLanchasCreadas + 1), MIN_MOTORES, MAX_COMBUSTIBLE, MIN_TRIPULANTES);
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el número de motores de la lancha.
     *
     * @return Número de motores.
     */
    public int getNumMotores() {
        return numeroMotores;
    }

    /**
     * Devuelve la cantidad de combustible en la lancha (en litros).
     *
     * @return Cantidad de combustible.
     */
    public int getCantidadCombustible() {
        return (int) cantidadCombustible; // Convertir a int
    }

    /**
     * Devuelve la cantidad de lanchas creadas hasta el momento.
     *
     * @return Número de lanchas creadas.
     */
    public static int getNumLanchas() {
        return numeroLanchasCreadas;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Cambia el rumbo de la lancha.
     *
     * @param nuevoRumbo El nuevo rumbo.
     * @throws NullPointerException Si el rumbo es nulo.
     * @throws IllegalArgumentException Si el rumbo no es válido.
     */
    @Override
    public void setRumbo(String nuevoRumbo) {
        if (nuevoRumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }
        if (!nuevoRumbo.equals("norte") && !nuevoRumbo.equals("sur") && !nuevoRumbo.equals("este") && !nuevoRumbo.equals("oeste")) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }

        super.setRumbo(nuevoRumbo);
    }

    /**
     * Inicia la navegación de la lancha.
     *
     * @param velocidad Velocidad de navegación (en nudos).
     * @param rumbo Rumbo de navegación.
     * @param patron Nombre del patrón.
     * @param tripulacion Número de tripulantes.
     * @throws IllegalStateException Si el nivel de combustible no es válido.
     * @throws IllegalArgumentException Si la velocidad no es válida.
     */
    @Override
    public void iniciarNavegacion(double velocidad, String rumbo, String patron, int tripulacion) {
        if (cantidadCombustible < MIN_COMBUSTIBLE || cantidadCombustible > MAX_COMBUSTIBLE) {
            throw new IllegalStateException("La lancha " + getNombreBarco() + " no tiene un nivel de combustible válido para iniciar la navegación (solo " + cantidadCombustible + " litros).");
        }
        if (velocidad < MIN_VELOCIDAD_LANCHA || velocidad > MAX_VELOCIDAD_LANCHA) {
            throw new IllegalArgumentException("La velocidad de navegación de " + velocidad + " nudos asignada a " + getNombreBarco() + " es incorrecta.");
        }

        super.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
    }

    /**
     * Detiene la navegación de la lancha.
     *
     * @param tiempoNavegacion Tiempo que ha estado navegando (en horas).
     */
    @Override
    public void pararNavegacion(double tiempoNavegacion) {
        double combustibleConsumido = getVelocidad() * tiempoNavegacion * FACTOR_COMBUSTIBLE;
        cantidadCombustible -= combustibleConsumido;
        if (cantidadCombustible < 0) {
            cantidadCombustible = 0;
        }

        super.pararNavegacion(tiempoNavegacion);
    }

    // ------------------------------------------------------------------------
    // Método Abstracto sobreescrito
    // ------------------------------------------------------------------------
    /**
     * Señaliza la lancha con bocinas y luces intermitentes.
     */
    @Override
    public void señalizar() {
        System.out.println("AVISO de señalización de la lancha " + getNombreBarco() + " con bocinas y luces intermitentes.");
    }

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve una cadena que representa el estado de la lancha.
     *
     * @return Estado de la lancha en forma de cadena.
     */
    @Override
    public String toString() {
        return super.toString() + ", Número de motores: " + numeroMotores
                + ", Nivel de combustible: " + String.format("%d", (int) cantidadCombustible) + " litros";
    }

}
