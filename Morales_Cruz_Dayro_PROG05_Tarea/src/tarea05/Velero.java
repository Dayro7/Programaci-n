package tarea05;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------

/**
 * Clase que representa un velero con atributos y métodos para gestionar su
 * estado y su navegación.
 * 
 * El velero tiene atributos como nombre, número de mástiles, tripulación y más,
 * que determinan sus características y comportamiento durante la navegación.
 * 
 * @author Dayro_Morales_Cruz
 */
public class Velero {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // ------------------------------------------------------------------------

    /**
     * Valor mínimo de mástiles permitido para el velero.
     */
    public static final int MIN_MASTILES = 1;

    /**
     * Valor máximo de mástiles permitido para el velero.
     */
    public static final int MAX_MASTILES = 4;

    /**
     * Velocidad mínima permitida para el velero.
     */
    public static final int MIN_VELOCIDAD = 2;

    /**
     * Velocidad máxima permitida para el velero.
     */
    public static final int MAX_VELOCIDAD = 30;

    /**
     * Patrón por defecto asignado a los veleros que no tienen un patrón específico.
     */
    public static final String PATRON_POR_DEFECTO = "Sin patrón";

    /**
     * Rumbo por defecto asignado a los veleros que no tienen un rumbo específico.
     */
    public static final String RUMBO_POR_DEFECTO = "Sin rumbo";

    /**
     * Número mínimo de tripulantes permitido en un velero.
     */
    public static final int MIN_TRIPULANTES = 0;

    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // ------------------------------------------------------------------------

    /**
     * Número total de veleros creados.
     */
    private static int numBarcos = 0;

    /**
     * Número de veleros que están actualmente navegando.
     */
    private static int numBarcosNavegando = 0;

    /**
     * Tiempo total de navegación acumulado por todos los veleros.
     */
    private static double tiempoTotalNavegacion = 0;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // ------------------------------------------------------------------------

    /**
     * Nombre del velero.
     */
    private final String nombreBarco;

    /**
     * Número de mástiles del velero.
     */
    private final int numMastiles;

    /**
     * Número máximo de tripulantes que puede tener el velero.
     */
    private final int maxTripulantes;

    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // ------------------------------------------------------------------------

    /**
     * Indica si el velero está navegando o no.
     */
    private boolean navegando;

    /**
     * Tiempo total de navegación acumulado por este velero.
     */
    private double tiempoTotalNavegacionBarco;

    /**
     * Velocidad actual del velero.
     */
    private int velocidad;

    /**
     * Nombre del patrón del velero.
     */
    private String patron;

    /**
     * Rumbo en el que está navegando el velero.
     */
    private String rumbo;

    /**
     * Número de tripulantes actuales del velero.
     */
    private int tripulacion;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------

    /**
     * Constructor que inicializa el velero con el nombre, número de mástiles y 
     * número máximo de tripulantes especificados.
     * 
     * @param nombreBarco Nombre del velero.
     * @param numMastiles Número de mástiles del velero.
     * @param maxTripulantes Númeroo máximo de tripulantes del velero.
     * @throws IllegalArgumentException Si el número de mástiles o tripulantes no es válido.
     */
    public Velero(String nombreBarco, int numMastiles, int maxTripulantes) {
        if (numMastiles < MIN_MASTILES || numMastiles > MAX_MASTILES) {
            throw new IllegalArgumentException("Número de mástiles fuera del rango permitido.");
        }
        if (maxTripulantes < MIN_TRIPULANTES) {
            throw new IllegalArgumentException("El número máximo de tripulantes debe ser al menos 0.");
        }

        this.nombreBarco = nombreBarco;
        this.numMastiles = numMastiles;
        this.maxTripulantes = maxTripulantes;
        this.navegando = false;
        this.tiempoTotalNavegacionBarco = 0;
        this.velocidad = 0;
        this.patron = PATRON_POR_DEFECTO;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.tripulacion = 0;

        numBarcos++;
    }

    /**
     * Constructor por defecto que inicializa un velero sin nombre, con 1 mástil
     * y 0 tripulantes.
     */
    public Velero() {
        this("Velero sin nombre", MIN_MASTILES, 0);
    }

    /**
     * Método estático que crea un array de veleros con el número especificado de veleros.
     * 
     * @param cantidad Número de veleros a crear.
     * @return Un array con los veleros creados.
     * @throws IllegalArgumentException Si la cantidad de veleros es inválida.
     */
    public static Velero[] crearArrayVelero(int cantidad) {
        if (cantidad < 1 || cantidad > 10) {
            throw new IllegalArgumentException("Número de barcos incorrecto (" + cantidad + "), debe ser mayor o igual que 1 y menor o igual que 10.");
        }
        Velero[] arrayVeleros = new Velero[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arrayVeleros[i] = new Velero("Velero " + (i + 1), MIN_MASTILES, 0);
        }
        return arrayVeleros;
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------

    /**
     * Obtiene el nombre del velero.
     * 
     * @return El nombre del velero.
     */
    public String getNombreBarco() {
        return nombreBarco;
    }

    /**
     * Obtiene el número de mástiles del velero.
     * 
     * @return El número de mástiles del velero.
     */
    public int getNumMastiles() {
        return numMastiles;
    }

    /**
     * Obtiene el número máximo de tripulantes del velero.
     * 
     * @return El número máximo de tripulantes del velero.
     */
    public int getMaxTripulantes() {
        return maxTripulantes;
    }

    /**
     * Indica si el velero está navegando o no.
     * 
     * @return True si el velero está navegando, False en caso contrario.
     */
    public boolean isNavegando() {
        return navegando;
    }

    /**
     * Obtiene el tiempo total de navegación acumulado por este velero.
     * 
     * @return El tiempo total de navegación del velero.
     */
    public double getTiempoTotalNavegacionBarco() {
        return tiempoTotalNavegacionBarco;
    }

    /**
     * Obtiene la velocidad actual del velero.
     * 
     * @return La velocidad del velero.
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Obtiene el nombre del patrón del velero.
     * 
     * @return El patrón del velero.
     */
    public String getPatron() {
        return patron;
    }

    /**
     * Obtiene el rumbo actual del velero.
     * 
     * @return El rumbo del velero.
     */
    public String getRumbo() {
        return rumbo;
    }

    /**
     * Obtiene el número de tripulantes actuales del velero.
     * 
     * @return El número de tripulantes del velero.
     */
    public int getTripulacion() {
        return tripulacion;
    }

    /**
     * Obtiene el número total de veleros creados.
     * 
     * @return El número total de veleros creados.
     */
    public static int getNumBarcos() {
        return numBarcos;
    }

    /**
     * Obtiene el número de veleros que están actualmente navegando.
     * 
     * @return El número de veleros navegando.
     */
    public static int getNumBarcosNavegando() {
        return numBarcosNavegando;
    }

    /**
     * Obtiene el tiempo total de navegación acumulado por todos los veleros.
     * 
     * @return El tiempo total de navegación.
     */
    public static double getTiempoTotalNavegacion() {
        return tiempoTotalNavegacion;
    }

    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------

    /**
     * Modifica el rumbo del velero mientras esté navegando.
     * 
     * @param rumbo Nuevo rumbo del velero.
     * @throws IllegalStateException Si el velero no está navegando o si ya navega con el mismo rumbo.
     * @throws IllegalArgumentException Si el rumbo no es válido.
     */
    public void setRumbo(String rumbo) {
        if (!navegando) {
            throw new IllegalStateException("El velero " + this.nombreBarco + " no está navegando, no se puede cambiar el rumbo.");
        }
        if (rumbo == null || (!rumbo.equals("ceñida") && !rumbo.equals("empopada"))) {
            throw new IllegalArgumentException("El rumbo no es válido, debe ser 'ceñida' o 'empopada'.");
        }
        if (this.rumbo.equals(rumbo)) {
            throw new IllegalStateException("El velero " + this.nombreBarco + " ya está navegando con ese rumbo.");
        }
        this.rumbo = rumbo;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------

    /**
     * Inicia la navegación del velero con los parámetros especificados.
     * 
     * @param velocidad Velocidad a la que navegará el velero.
     * @param rumbo Rumbo en el que navegará el velero.
     * @param patron Nombre del patrón del velero.
     * @param tripulacion Número de tripulantes a bordo del velero.
     * @throws IllegalStateException Si el velero ya está navegando.
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido.
     */
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulacion) {
        if (navegando) {
            throw new IllegalStateException("El velero " + this.nombreBarco + " ya está navegando.");
        }
        if (velocidad < MIN_VELOCIDAD || velocidad > MAX_VELOCIDAD) {
            throw new IllegalArgumentException("La velocidad no es válida.");
        }
        if (rumbo == null || rumbo.isEmpty()) {
            throw new IllegalArgumentException("El rumbo no puede ser nulo ni vacío.");
        }
        if (patron == null || patron.isEmpty()) {
            throw new IllegalArgumentException("El patrón no puede ser nulo ni vacío.");
        }
        if (tripulacion < MIN_TRIPULANTES || tripulacion > maxTripulantes) {
            throw new IllegalArgumentException("El número de tripulantes no es válido.");
        }

        this.velocidad = velocidad;
        this.rumbo = rumbo;
        this.patron = patron;
        this.tripulacion = tripulacion;
        this.navegando = true;

        numBarcosNavegando++;
        System.out.println(" -> El barco " + this.nombreBarco + " ha iniciado la navegación.");
    }

    /**
     * Detiene la navegación del velero, actualizando el tiempo total de navegación.
     * 
     * @param tiempo Tiempo que el velero ha estado navegando.
     * @throws IllegalStateException Si el velero no está navegando.
     * @throws IllegalArgumentException Si el tiempo es inválido.
     */
    public void pararNavegacion(double tiempo) {
        if (!navegando) {
            throw new IllegalStateException("El velero " + this.nombreBarco + " no está navegando.");
        }
        if (tiempo <= 0) {
            throw new IllegalArgumentException("Tiempo navegando incorrecto.");
        }

        this.tiempoTotalNavegacionBarco += tiempo;
        tiempoTotalNavegacion += tiempo;
        this.navegando = false;

        numBarcosNavegando--;
        System.out.println(" -> El barco " + this.nombreBarco + " ha detenido la navegación.");
    }

    /**
     * Inicia una regata entre este velero y otro.
     * 
     * @param otroBarco El otro velero con el que se realizará la regata.
     * @return El nombre del velero ganador de la regata.
     * @throws NullPointerException Si el otro barco es nulo.
     * @throws IllegalStateException Si alguno de los barcos no está navegando o los barcos no tienen las mismas condiciones.
     */
    public String iniciarRegata(Velero otroBarco) {
        if (otroBarco == null) {
            throw new NullPointerException("El barco con el que se intenta regatear no existe.");
        }
        if (!this.navegando || !otroBarco.isNavegando()) {
            throw new IllegalStateException("No se puede iniciar la regata, uno de los barcos no está navegando.");
        }
        if (!this.rumbo.equals(otroBarco.getRumbo())) {
            throw new IllegalStateException("Los barcos deben navegar con el mismo rumbo.");
        }
        if (this.numMastiles != otroBarco.getNumMastiles()) {
            throw new IllegalStateException("Los barcos no tienen el mismo número de mástiles.");
        }

        String ganador = this.velocidad > otroBarco.getVelocidad() ? this.nombreBarco : otroBarco.getNombreBarco();
        if (this.velocidad == otroBarco.getVelocidad()) {
            System.out.println("Ambos barcos han llegado a la vez a la línea de llegada.");
        } else {
            System.out.println("El barco " + ganador + " ha llegado antes.");
        }
        return ganador;
    }

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------

    /**
     * Devuelve una representación en formato de cadena del velero, con su estado
     * actual.
     * 
     * @return Una cadena con la descripción del velero.
     */
    @Override
public String toString() {
    String estadoNavegando = navegando ? "Sí" : "No";
    
    // Convertir el tiempo total de navegación de minutos a horas y minutos
    int horas = (int) (tiempoTotalNavegacionBarco / 60);  // obtener las horas
    int minutos = (int) (tiempoTotalNavegacionBarco % 60);  // obtener los minutos restantes

    // Devolver la representación en formato de cadena con el tiempo en horas y minutos
    return String.format("{Nombre: %s, Mástiles: %d, Tripulación: %d, Navegando: %s, Patrón: %s, Rumbo: %s, Velocidad: %d nudos, Tiempo total: %d horas y %d minutos} ",
                nombreBarco, numMastiles, tripulacion, estadoNavegando, patron, rumbo, velocidad, horas, minutos);
}
}
