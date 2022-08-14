package com.vacunas.inventario.mapper;

import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.entity.Empleado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iEmpleadoMapper {
    @Mappings({
            @Mapping(source = "idEmpleado",target="idEmpleado"),
            @Mapping(source = "cedula",target="cedula"),
            @Mapping(source = "nombres",target="nombres"),
            @Mapping(source = "apellidos",target="apellidos"),
            @Mapping(source = "email",target="email"),
            @Mapping(source = "fechaNacimiento",target="fechaNacimiento"),
            @Mapping(source = "direccion",target="direccion"),
            @Mapping(source = "telefono",target="telefono"),
            @Mapping(source = "estadoVacuna",target="estadoVacuna"),
            @Mapping(source = "vacunas",target="vacunas"),
            @Mapping(source = "cuenta",target="cuenta"),
    })
    EmpleadoDTO toEmpleadoDTO(Empleado empleado);
    List<EmpleadoDTO> toEmpleadosDTO(List<Empleado> empleadoList);

    @InheritInverseConfiguration
    Empleado toEmpleado(EmpleadoDTO empleadoDTO);
    List<Empleado> toEmpleados(List<EmpleadoDTO> empleadoDTOList);
}
