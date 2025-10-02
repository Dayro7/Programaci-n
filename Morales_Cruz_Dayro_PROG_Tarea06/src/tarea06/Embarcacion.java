package tarea06;

/**
 * Clase que representa una embarcación genérica.
 *
 * Esta clase es la base para todas las embarcaciones en el puerto deportivo.
 * Las subclases pueden extender esta clase para añadir más detalles
 * específicos.
 */
public abstract class Embarcacion {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (constantes)
    // ------------------------------------------------------------------------
    /**
     * Nombre por defecto del patrón durante la navegación.
     */
    public static final String PATRON_POR_DEFECTO = "Sin patrón";

    /**
     * Rumbo por defecto durante la navegación.
     */
    public static final String RUMBO_POR_DEFECTO = "Sin rumbo";

    /**
     * Número mínimo de tripulantes permitido.
     */
    public static final int MIN_TRIPULANTES = 0;

    // ------------------------------------------------------------------------
    // Atributos estáticos privados (variables)
    // ------------------------------------------------------------------------
    /**
     * Cantidad total de barcos creados.
     */
    private static int numeroBarcosCreados = 0;

    /**
     * Número de barcos que están navegando ahora mismo.
     */
    private static int numeroBarcosNavegando = 0;

    /**
     * Tiempo total de navegación de todos los barcos (en horas).
     */
    private static double tiempoTotalNavegacion = 0.0;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // ------------------------------------------------------------------------
    /**
     * Nombre del barco.
     */
    private final String nombreBarco;

    /**
     * Número máximo de tripulantes que puede tener el barco.
     */
    private final int numeroMaximoTripulantes;

    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // ------------------------------------------------------------------------
    /**
     * Indica si el barco está navegando.
     */
    private boolean navegando;

    /**
     * Velocidad del barco (en nudos).
     */
    private double velocidadNavegacion;

    /**
     * Nombre del patrón del barco.
     */
    private String nombrePatron;

    /**
     * Rumbo actual del barco.
     */
    private String rumbo;

    /**
     * Número de tripulantes a bordo (excluyendo el patrón).
     */
    private int numeroTripulantes;

    /**
     * Tiempo total de navegación del barco (en horas).
     */
    private double tiempoTotalNavegacionBarco;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor para crear una nueva embarcación.
     *
     * @param nombreBarco Nombre del barco.
     * @param numeroMaximoTripulantes Número máximo de tripulantes.
     * @throws NullPointerException Si el nombre es null.
     * @throws IllegalArgumentException Si el nombre está vacío o el número de
     * tripulantes es menor que el mínimo permitido.
     */
    public Embarcacion(String nombreBarco, int numeroMaximoTripulantes) {
        if (nombreBarco == null) {
            throw new NullPointerException("El nombre de la embarcación no puede ser nulo.");
        }
        if (nombreBarco.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la embarcación no puede estar vacío.");
        }
        if (numeroMaximoTripulantes < MIN_TRIPULANTES) {
            throw new IllegalArgumentException("El número de tripulantes debe ser, como mínimo, " + MIN_TRIPULANTES + ".");
        }

        this.nombreBarco = nombreBarco;
        this.numeroMaximoTripulantes = numeroMaximoTripulantes;
        this.navegando = false;
        this.velocidadNavegacion = 0.0;
        this.nombrePatron = PATRON_POR_DEFECTO;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.numeroTripulantes = MIN_TRIPULANTES;
        this.tiempoTotalNavegacionBarco = 0.0;

        numeroBarcosCreados++;
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el nombre del barco.
     *
     * @return Nombre del barco.
     */
    public String getNombreBarco() {
        return nombreBarco;
    }

    /**
     * Devuelve el número máximo de tripulantes.
     *
     * @return Número máximo de tripulantes.
     */
    public int getMaxTripulantes() {
        return numeroMaximoTripulantes;
    }

    /**
     * Indica si el barco está navegando.
     *
     * @return true si está navegando, false si no.
     */
    public boolean isNavegando() {
        return navegando;
    }

    /**
     * Devuelve la velocidad del barco en nudos.
     *
     * @return Velocidad en nudos.
     */
    public int getVelocidad() {
        return (int) velocidadNavegacion;
    }

    /**
     * Devuelve el rumbo del barco.
     *
     * @return Rumbo del barco.
     */
    public String getRumbo() {
        return rumbo;
    }

    /**
     * Devuelve el nombre del patrón.
     *
     * @return Nombre del patrón.
     */
    public String getPatron() {
        return nombrePatron;
    }

    /**
     * Devuelve el número de tripulantes (excluyendo el patrón).
     *
     * @return Número de tripulantes.
     */
    public int getTripulacion() {
        return numeroTripulantes;
    }

    /**
     * Devuelve el tiempo total de navegación del barco en minutos.
     *
     * @return Tiempo total en minutos.
     */
    public int getTiempoTotalNavegacionBarco() {
        return (int) (tiempoTotalNavegacionBarco * 60); // Convertir a minutos
    }

    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
    /**
     * Devuelve la cantidad de barcos creados hasta ahora.
     *
     * @return Número de barcos creados.
     */
    public static int getNumBarcos() {
        return numeroBarcosCreados;
    }

    /**
     * Devuelve la cantidad de barcos que están navegando actualmente.
     *
     * @return Número de barcos navegando.
     */
    public static int getNumBarcosNavegando() {
        return numeroBarcosNavegando;
    }

    /**
     * Devuelve el tiempo total de navegación acumulado por todos los barcos en
     * minutos.
     *
     * @return Tiempo total en minutos.
     */
    public static float getTiempoTotalNavegacion() {
        return (float) (tiempoTotalNavegacion * 60); // Convertir a minutos
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Cambia el rumbo del barco.
     *
     * @param nuevoRumbo El nuevo rumbo.
     * @throws IllegalStateException Si el barco no está navegando.
     * @throws NullPointerException Si el rumbo es nulo.
     * @throws IllegalArgumentException Si el rumbo está vacío o es el mismo que
     * el actual.
     */
    public void setRumbo(String nuevoRumbo) {
        if (!navegando) {
            throw new IllegalStateException("La embarcación " + nombreBarco + " no está navegando, no se puede cambiar el rumbo.");
        }
        if (nuevoRumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.");
        }
        if (nuevoRumbo.isEmpty()) {
            throw new IllegalArgumentException("El rumbo no puede estar vacío, debes indicar el rumbo para iniciar la navegación.");
        }
        if (nuevoRumbo.equals(rumbo)) {
            throw new IllegalStateException("La embarcación " + nombreBarco + " ya está navegando con ese rumbo (" + rumbo + "), debes indicar un rumbo distinto para poder modificarlo.");
        }

        this.rumbo = nuevoRumbo;
    }

    /**
     * Inicia la navegación del barco.
     *
     * @param velocidad Velocidad de navegación en nudos.
     * @param rumbo Rumbo de navegación.
     * @param patron Nombre del patrón.
     * @param tripulacion Número de tripulantes.
     * @throws IllegalStateException Si el barco ya está navegando.
     * @throws NullPointerException Si el rumbo o el patrón es nulo.
     * @throws IllegalArgumentException Si el rumbo o el patrón está vacío, o si
     * el número de tripulantes no es válido.
     */
    public void iniciarNavegacion(double velocidad, String rumbo, String patron, int tripulacion) {
        if (navegando) {
            throw new IllegalStateException("La embarcación " + nombreBarco + " ya está navegando y se encuentra fuera de puerto.");
        }
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.");
        }
        if (rumbo.isEmpty()) {
            throw new IllegalArgumentException("El rumbo no puede estar vacío, debes indicar el rumbo para iniciar la navegación.");
        }
        if (patron == null) {
            throw new NullPointerException("El patrón de la embarcación no puede ser nulo, se necesita un patrón para iniciar la navegación.");
        }
        if (patron.isEmpty()) {
            throw new IllegalArgumentException("El patrón de la embarcación no puede estar vacío, se necesita un patrón para iniciar la navegación.");
        }
        if (tripulacion < MIN_TRIPULANTES || tripulacion > numeroMaximoTripulantes) {
            throw new IllegalArgumentException("El número de tripulantes debe estar entre " + MIN_TRIPULANTES + " y " + numeroMaximoTripulantes + ".");
        }

        this.navegando = true;
        this.velocidadNavegacion = velocidad;
        this.rumbo = rumbo;
        this.nombrePatron = patron;
        this.numeroTripulantes = tripulacion;
        numeroBarcosNavegando++;
    }

    /**
     * Detiene la navegación del barco.
     *
     * @param tiempoNavegacion El tiempo que el barco ha estado navegando (en
     * horas).
     * @throws IllegalStateException Si el barco no está navegando.
     * @throws IllegalArgumentException Si el tiempo de navegación es menor o
     * igual a cero.
     */
    public void pararNavegacion(double tiempoNavegacion) {
        if (!navegando) {
            throw new IllegalStateException("La embarcación " + nombreBarco + " no está navegando.");
        }
        if (tiempoNavegacion <= 0) {
            throw new IllegalArgumentException("Tiempo navegando incorrecto, debe ser mayor que cero.");
        }

        this.navegando = false;
        this.tiempoTotalNavegacionBarco += tiempoNavegacion;
        tiempoTotalNavegacion += tiempoNavegacion;
        numeroBarcosNavegando--;

        // Reiniciamos el número de tripulantes a 0 al detener la navegación
        this.numeroTripulantes = MIN_TRIPULANTES;
    }

    // ------------------------------------------------------------------------
    // Método Abstracto (sin implementación)
    // ------------------------------------------------------------------------
    /**
     * Método abstracto para señalización específica de cada tipo de
     * embarcación.
     */
    public abstract void señalizar();

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve una cadena que representa el estado del barco.
     *
     * @return Estado del barco en forma de cadena.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la embarcación: ").append(nombreBarco).append(", ");
        sb.append("Tripulación: ").append(numeroTripulantes).append(", ");
        sb.append("¿Está navegando?: ").append(navegando ? "Sí" : "No").append(", ");

        if (navegando) {
            sb.append("con el patrón ").append(nombrePatron)
                    .append(" en ").append(rumbo)
                    .append(" a ").append(velocidadNavegacion)
                    .append(" nudos, ");
        }

        sb.append("Tiempo total de navegación del barco: ")
                .append(String.format("%.2f", tiempoTotalNavegacionBarco / 60))
                .append(" horas");

        return sb.toString();
    }
}
