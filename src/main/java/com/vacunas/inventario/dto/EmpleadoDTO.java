package com.vacunas.inventario.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.*;

@Data
public class EmpleadoDTO implements Serializable {
    private int idEmpleado;

    @NotNull(message = "Debe ingresar los nombres del empleado")
    @NotBlank(message = "Se necesita los nombres del empleado")
    @NotEmpty
    @Length(min=10, max=10)
    private String cedula;

    @NotNull(message = "Debe ingresar los nombres del empleado")
    @NotBlank(message = "Se necesita los nombres del empleado")
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z ]+",message="No se admiten números ni caracteres especiales")

    private String nombres;

    @NotNull(message = "Debe ingresar los apellidos del empleado")
    @NotBlank(message = "Se necesita los apellidos del empleado")
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z ]+",message="No se admiten números ni caracteres especiales")
    private String apellidos;

    @NotNull(message = "Debe ingresar el correo del empleado")
    @NotBlank(message = "Se necesita el correo del empleado")
    @NotEmpty
    @Email(message = "Formato de email no valido")
    private String email;

    @NotEmpty
    private LocalDate fechaNacimiento;

    @NotNull(message = "Debe ingresar el correo del empleado")
    @NotBlank(message = "Se necesita el correo del empleado")
    @NotEmpty
    private String direccion;

    @NotNull(message = "Debe ingresar el telefono del empleado")
    @NotBlank(message = "Se necesita el telefono del empleado")
    @NotEmpty
    private String telefono;

    @NotEmpty
    private boolean estadoVacuna;

    private Set<VacunaDTO> vacunas;

    private CuentaDTO cuenta;
}
