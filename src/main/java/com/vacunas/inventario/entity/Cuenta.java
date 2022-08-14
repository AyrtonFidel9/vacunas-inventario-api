package com.vacunas.inventario.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Cuentas")
public class Cuenta {
    @Id
    private String nombreUsuario;

    @OneToOne(mappedBy = "cuenta")
    private Empleado empleado;

    private String password;

    @Enumerated(EnumType.STRING)
    private RolCuenta rol;
}
