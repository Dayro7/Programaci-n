package tarea06;

/**
 * Interfaz que representa la capacidad de una embarcación para navegar.
 *
 * Esta interfaz define los métodos necesarios para iniciar y parar la
 * navegación. Las clases que implementen esta interfaz deberán proporcionar su
 * propia implementación de estos métodos.
 *
 * @autor Morales_Cruz_Dayro
 */
public interface Navegable {

    /**
     * Inicia la navegación de una embarcación.
     *
     * @param velocidad Velocidad de navegación (en nudos).
     * @param rumbo Rumbo de navegación.
     * @param patron Nombre del patrón.
     * @param tripulacion Número de tripulantes.
     */
    void iniciarNavegacion(double velocidad, String rumbo, String patron, int tripulacion);

    /**
     * Detiene la navegación de una embarcación.
     *
     * @param tiempoNavegacion Tiempo que ha estado navegando (en horas).
     */
    void pararNavegacion(double tiempoNavegacion);
}
