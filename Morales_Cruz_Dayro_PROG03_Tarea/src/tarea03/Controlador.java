package tarea03;

import libtarea3.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controlador {
    public static void main(String[] args) {
        
        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        Aeropuerto aeropuerto1, aeropuerto2, aeropuerto3;
        Aeronave avion1, avion2, avion3;

        //----------------------------------------------
        //          Creación de objetos
        //----------------------------------------------
        try {
            // Instanciar los 3 aeropuertos
            aeropuerto1 = new Aeropuerto("El prat", "Barcelona");
            aeropuerto2 = new Aeropuerto("Barajas", "Madrid");
            aeropuerto3 = new Aeropuerto("Federico García Lorca", "Granada");

            // Instanciar las 3 aeronaves
            avion1 = new Aeronave();  // Constructor por defecto
            avion2 = new Aeronave("EC-123", "Boing747");  // Constructor con parámetros
            avion3 = new Aeronave("EC-456", "Boing787", aeropuerto2);  // Constructor con parámetros

            // Mostrar que los aeropuertos y aeronaves han sido creados
            System.out.println("-------------------------");
            System.out.println("---Creando Aeropuertos---");
            System.out.println("-------------------------");
            System.out.println("\n **Aeropuertos creados** \n");

            System.out.println("-------------------------");
            System.out.println("----Creando Aeronaves----");
            System.out.println("-------------------------");
            System.out.println("\n **Aeronaves creadas** \n");

            //----------------------------------------------
            //   Secuencia de instrucciones
            //----------------------------------------------
            System.out.println("-------------------------");
            System.out.println("-Secuencia instrucciones-");
            System.out.println("-------------------------");

            // Avion1 despega con velocidad 1500, altitud 1750, rumbo 50 y fechaHora actual
            avion1.despegar(1500, 1750, 50, obtenerFechaHoraActual());
            System.out.println("\t Avión1 ha despegado\n");

            // Avion2 despega con velocidad 1500, altitud 1850, rumbo 75
            avion2.despegar(1500, 1850, 75, obtenerFechaHoraActual());
            System.out.println("\t Avión2 ha despegado\n");

            // Avion3 despega con velocidad 1500, altitud 1000, rumbo 180
            avion3.despegar(1500, 1000, 180, obtenerFechaHoraActual());
            System.out.println("\t Avión3 ha despegado\n");

            // Comprobar si Avion1 está volando
            System.out.printf("¿Avión1 está volando?: %b\n", avion1.isVolando());

            // Mostrar la matrícula del Avion2
            System.out.printf("Matricula avión2: %s\n", avion2.getMatricula());

            // Mostrar modelo del Avion3
            System.out.printf("Modelo avión3: %s\n", avion3.getModelo());

            // Modificar rumbo del Avión2 a 90º y mostrarlo
            avion2.setRumbo(90);
            System.out.printf("El rumbo del avión2 es: %d\n", avion2.getRumbo());

            // Avion3 aterriza en el aeropuerto de Barcelona después de 75 minutos
            avion3.aterrizar(aeropuerto1, 75);
            System.out.println("\n\t Avión3 ha aterrizado\n");

            // Avion2 aterriza en el aeropuerto de Madrid después de 80 minutos
            avion2.aterrizar(aeropuerto2, 80);
            System.out.println("\t Avión2 ha aterrizado\n");

            // Comprobar si Avion2 está volando
            System.out.printf("¿Avión2 está volando?: %b\n", avion2.isVolando());

            // Modificar altitud del Avion1 a 1250 metros y mostrarlo
            avion1.setAltitud(1250);
            System.out.printf("La nueva altitud del avión 1 es: %d\n", avion1.getAltitud());

            // Mostrar el tiempo total de vuelo del Avion2
            System.out.printf("El tiempo de vuelo del avión 2 en minutos es : %d\n", avion2.getTiempoTotalVuelo());

            // Mostrar el aeropuerto del Avion3
            System.out.printf("El aeropuerto del avión3 es: %s\n", avion3.getAeropuerto());
            
            // Mostrar la fecha y hora de despegue del Avion3
            System.out.printf("El despegue del avión3 se realizó con fecha: %s\n", avion3.getFechaHoraDespegue());

            // Avion3 despega con velocidad 860, altitud 1420, rumbo 270
            avion3.despegar(860, 1420, 270, obtenerFechaHoraActual());
            System.out.println("\n\t Avión3 ha despegado\n");

            // Avion1 aterriza en el aeropuerto de Granada después de 260 minutos
            avion1.aterrizar(aeropuerto3, 260);
            System.out.println("\t Avión1 ha aterizado\n");

            // Modificar velocidad del Avion3 a 950 km/h y mostrarlo
            avion3.setVelocidad(950);
            System.out.printf("La nueva velocidad del avión3 es: %d\n", avion3.getVelocidad());

            // Mostrar el nombre del aeropuerto de Madrid
            System.out.printf("El nombre del aeropuerto de Madrid es: %s\n", aeropuerto2.getNombre());

            // Mostrar el número de aeronaves en el aeropuerto de Granada
            System.out.printf("El número de aeronaves en el aeropuerto de Granada es: %d\n", aeropuerto3.getNumeroAeronaves());

            // Mostrar toda la información del Avion1, Avion2 y Avion3
            System.out.printf("Avión1: %s\n", avion1.toString());
            System.out.printf("Avión2: %s\n", avion2.toString());
            System.out.printf("Avión3: %s\n", avion3.toString());

            //----------------------------------------------
            //          Llamadas a métodos estáticos
            //----------------------------------------------

            // Mostrar el número total de aeronaves volando en ese momento
            System.out.println("-----------------------");
            System.out.println("---Métodos estáticos---");
            System.out.println("-----------------------");
            System.out.printf("El número total de aeronaves volando ahora mismo es: %d\n", Aeronave.getNumAeronavesVolando());

            // Mostrar el tiempo total de aeronaves volando en horas
            System.out.printf("El tiempo total de aeronaves volando en horas es: %.6f\n", Aeronave.getNumHorasVuelo());

            // Mostrar el número total de aeronaves
            System.out.printf("El número total de aeronaves es: %d\n", Aeronave.getNumAeronaves());

        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    // Método para obtener la fecha y hora actual
    public static String obtenerFechaHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
