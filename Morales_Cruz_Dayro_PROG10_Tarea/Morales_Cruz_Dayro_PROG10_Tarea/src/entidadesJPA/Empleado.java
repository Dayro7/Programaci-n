/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesJPA;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * Entidad JPA que representa a un empleado dentro del sistema de gestión del
 * supermercado.
 *
 * <p>
 * Incluye información sobre el ID del empleado, su nombre, salario anual y la
 * sección en la que trabaja.</p>
 *
 * @author Dayro Morales Cruz
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    @Id
    @Column(name = "id_empleado", length = 4, nullable = false)
    private String idEmpleado;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "salario_anual", nullable = false)
    private int salarioAnual;

    @ManyToOne
    @JoinColumn(name = "id_seccion", nullable = false)
    private Seccion seccion;

    // Constructor vacío requerido por JPA
    public Empleado() {
    }

    public Empleado(String idEmpleado, String nombre, int salarioAnual, Seccion seccion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioAnual = salarioAnual;
        this.seccion = seccion;
    }

    // Getters y Setters
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalarioAnual() {
        return salarioAnual;
    }

    public void setSalarioAnual(int salarioAnual) {
        this.salarioAnual = salarioAnual;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return "Empleado{"
                + "idEmpleado='" + idEmpleado + '\''
                + ", nombre='" + nombre + '\''
                + ", salarioAnual=" + salarioAnual
                + ", seccion=" + seccion.getDescripcion() + '}';
    }
}
