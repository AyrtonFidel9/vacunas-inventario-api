package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.CuentaDTO;

public interface iCuentaService {
    CuentaDTO generarCredenciales(int idEmpleado);

    CuentaDTO actualizarInformacion(String nombreUsuario, CuentaDTO cuentaDTO);

    CuentaDTO buscarCuenta(String nombreUsuario);

    void eliminarCuenta(String nombreUsuario);
}
