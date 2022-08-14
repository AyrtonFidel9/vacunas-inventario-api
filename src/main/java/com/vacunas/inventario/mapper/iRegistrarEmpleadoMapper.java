package com.vacunas.inventario.mapper;

import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.dto.RegistrarEmpleadoDTO;
import com.vacunas.inventario.entity.Empleado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface iRegistrarEmpleadoMapper {
    @Mappings({
            @Mapping(source = "idEmpleado",target="idEmpleado"),
            @Mapping(source = "cedula",target="cedula"),
            @Mapping(source = "nombres",target="nombres"),
            @Mapping(source = "email",target="email"),
            @Mapping(source = "apellidos",target="apellidos")
    })
    RegistrarEmpleadoDTO toRegistrarEmpleadoDTO(Empleado empleado);

    @InheritInverseConfiguration
    @Mapping(target="fechaNacimiento", ignore = true)
    @Mapping(target="direccion", ignore = true)
    @Mapping(target="telefono", ignore = true)
    @Mapping(target="estadoVacuna",  ignore = true)
    @Mapping(target="vacunas", ignore = true)
    @Mapping(target="cuenta", ignore = true)
    Empleado toEmpleadoRegistrado(RegistrarEmpleadoDTO registrarEmpleadoDTO);
}
