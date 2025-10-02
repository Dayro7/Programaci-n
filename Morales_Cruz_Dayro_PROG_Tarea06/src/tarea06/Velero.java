package tarea06;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * Clase que representa un velero.
 *
 * Esta clase extiende la funcionalidad de la clase Embarcacion y añade
 * atributos y métodos específicos para los veleros. Implementa la interfaz
 * Regateable. No se pueden crear subclases a partir de esta clase.
 *
 * @autor Morales_Cruz_Dayro
 */
public final class Velero extends Embarcacion implements Regateable {

    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    public static final int MIN_MASTILES = 1;
    public static final int MAX_MASTILES = 4;
    public static final int MIN_VELOCIDAD_VELERO = 2; // en nudos
    public static final int MAX_VELOCIDAD_VELERO = 30; // en nudos
    private static int numeroVelerosCreados = 0;
    private final int numeroMastiles;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    public Velero(String nombreBarco, int numeroMastiles, int numeroMaximoTripulantes) {
        super(nombreBarco, numeroMaximoTripulantes);

        if (numeroMastiles < MIN_MASTILES || numeroMastiles > MAX_MASTILES) {
            throw new IllegalArgumentException("El número de mástiles debe estar entre " + MIN_MASTILES + " y " + MAX_MASTILES + ".");
        }

        this.numeroMastiles = numeroMastiles;
        numeroVelerosCreados++;
    }

    public Velero() {
        this("Velero " + (numeroVelerosCreados + 1), MIN_MASTILES, MIN_TRIPULANTES);
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    public int getNumMastiles() {
        return numeroMastiles;
    }

    public static int getNumVeleros() {
        return numeroVelerosCreados;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    @Override
    public void setRumbo(String nuevoRumbo) {
        if (nuevoRumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        if (!nuevoRumbo.equals("ceñida") && !nuevoRumbo.equals("empopada")) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }

        super.setRumbo(nuevoRumbo);
    }

    @Override
    public void iniciarNavegacion(double velocidad, String rumbo, String patron, int tripulacion) {
        if (velocidad < MIN_VELOCIDAD_VELERO || velocidad > MAX_VELOCIDAD_VELERO) {
            throw new IllegalArgumentException("La velocidad de navegación de " + velocidad + " nudos es incorrecta.");
        }

        super.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
    }

    @Override
    public String iniciarRegata(Velero otroVelero) {
        if (otroVelero == null) {
            throw new NullPointerException("El barco con el que se intenta regatear no existe");
        }
        if (!this.isNavegando()) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + this.getNombreBarco() + " no está navegando.");
        }
        if (!otroVelero.isNavegando()) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + otroVelero.getNombreBarco() + " no está navegando.");
        }
        if (!this.getRumbo().equals(otroVelero.getRumbo())) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " deben navegar con el mismo rumbo.");
        }
        if (this.getNumMastiles() != otroVelero.getNumMastiles()) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " no tienen el mismo numero de mástiles.");
        }

        if (this.getVelocidad() > otroVelero.getVelocidad()) {
            return "El barco " + this.getNombreBarco() + " ha llegado antes a la línea de llegada.";
        } else if (this.getVelocidad() < otroVelero.getVelocidad()) {
            return "El barco " + otroVelero.getNombreBarco() + " ha llegado antes a la línea de llegada.";
        } else {
            return "Los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " han llegado a la vez a la línea de llegada.";
        }
    }

    @Override
    public void señalizar() {
        System.out.println("AVISO del velero " + getNombreBarco() + " con banderas de señalización marítima.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Número de mástiles: " + numeroMastiles;
    }

    // Ejemplo de método que calcula el tiempo total de navegación en minutos
    public int calcularTiempoNavegacion(int horas, int minutos) {
        int totalMinutos = horas * 60 + minutos;
        return totalMinutos;
    }

    public static void main(String[] args) {
        // Prueba del método calcularTiempoNavegacion
        Velero velero = new Velero("Atlantis", 2, 5);
        int horas = 2;
        int minutos = 0;
        int tiempoTotalMinutos = velero.calcularTiempoNavegacion(horas, minutos);
        System.out.println("Tiempo total de navegación (en minutos): " + tiempoTotalMinutos);
    }
}
