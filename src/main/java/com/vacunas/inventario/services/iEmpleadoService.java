package com.vacunas.inventario.services;

import com.vacunas.inventario.controller.EmpleadoController;
import com.vacunas.inventario.dto.CuentaDTO;
import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.dto.RegistrarEmpleadoDTO;

import java.time.LocalDate;
import java.util.List;

public interface iEmpleadoService {

    EmpleadoDTO verInformacion(int idEmpleado);

    List<EmpleadoDTO> listarEmpleados();
    void eliminarEmpleado(int idEmpleado);

    EmpleadoDTO ingresarEmpleado(EmpleadoDTO empleadoDTO);

    RegistrarEmpleadoDTO registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO);

    EmpleadoDTO actualizarEmpleado(int idEmpleado, EmpleadoDTO empleadoDTO);

    List<EmpleadoDTO> filtrarPorTipoVacuna(String tipoVacuna);

    List<EmpleadoDTO> filtrarPorEstadoVacuna(boolean estadoVacuna);

    List<EmpleadoDTO> filtrarPorRangoFechaVacuna(LocalDate fInicio, LocalDate fFin);
}
