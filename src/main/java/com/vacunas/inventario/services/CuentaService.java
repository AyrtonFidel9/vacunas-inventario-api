package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.CuentaDTO;
import org.springframework.stereotype.Service;

@Service
public class CuentaService implements iCuentaService{
    @Override
    public CuentaDTO generarCredenciales(CuentaDTO cuentaDTO) {
        return null;
    }

    @Override
    public CuentaDTO actualizarInformacion(String nombreUsuario, CuentaDTO cuentaDTO) {
        return null;
    }

    @Override
    public CuentaDTO buscarCuenta(String nombreUsuario) {
        return null;
    }

    @Override
    public CuentaDTO eliminarCuenta(String nombreUsuario) {
        return null;
    }
}
