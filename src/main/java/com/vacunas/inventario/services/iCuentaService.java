package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.CuentaDTO;

public interface iCuentaService {
    CuentaDTO generarCredenciales(CuentaDTO cuentaDTO);

    CuentaDTO actualizarInformacion(String nombreUsuario, CuentaDTO cuentaDTO);

    CuentaDTO buscarCuenta(String nombreUsuario);

    CuentaDTO eliminarCuenta(String nombreUsuario);
}
