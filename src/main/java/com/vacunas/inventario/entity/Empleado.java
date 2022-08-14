package com.vacunas.inventario.entity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    private Cuenta cuenta;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Set<Vacuna> vacunas;

    @Column(unique = true)
    private String cedula;

    private String nombres;

    private String apellidos;

    private String email;

    private LocalDate fechaNacimiento;

    private String direccion;

    private String telefono;

    private boolean estadoVacuna;
}
