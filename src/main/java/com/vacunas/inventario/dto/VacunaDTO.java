package com.vacunas.inventario.dto;

import com.vacunas.inventario.entity.TipoVacuna;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class VacunaDTO implements Serializable {

    private int idVacuna;

    private int idEmpleado;

    private TipoVacuna tipo;

    private LocalDate fecha;

    private int numDosis;
}
