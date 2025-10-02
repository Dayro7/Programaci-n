package tarea06;

/**
 * Interfaz que representa la capacidad de una embarcación para participar en
 * una regata.
 *
 * Esta interfaz define el método necesario para iniciar una regata entre dos
 * veleros. Las clases que implementen esta interfaz deberán proporcionar su
 * propia implementación de este método.
 *
 * @autor Morales_Cruz_Dayro
 */
public interface Regateable {

    /**
     * Inicia una regata entre dos veleros.
     *
     * @param otroVelero El otro velero con el que competir.
     * @return El resultado de la regata.
     */
    String iniciarRegata(Velero otroVelero);
}
