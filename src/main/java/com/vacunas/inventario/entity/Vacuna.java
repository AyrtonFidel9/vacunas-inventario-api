package com.vacunas.inventario.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="Vacunas")
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVacuna;

    @ManyToOne
    @JoinColumn(name="idEmpleado", referencedColumnName = "idEmpleado")
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private TipoVacuna tipo;

    private LocalDate fecha;

    private int numDosis;

}
