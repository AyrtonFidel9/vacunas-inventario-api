package com.vacunas.inventario.dto;
import com.vacunas.inventario.entity.RolCuenta;
import lombok.Data;

import java.io.Serializable;

@Data
public class CuentaDTO implements Serializable {


    private String nombreUsuario;

    private String password;

    private RolCuenta rol;
}
