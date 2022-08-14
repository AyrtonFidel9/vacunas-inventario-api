package com.vacunas.inventario.mapper;

import com.vacunas.inventario.dto.CuentaDTO;
import com.vacunas.inventario.entity.Cuenta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iCuentaMapper {
    @Mappings({
            @Mapping(source="nombreUsuario",target="nombreUsuario"),
            @Mapping(source="password",target="password"),
            @Mapping(source="rol",target="rol")
    })
    CuentaDTO toCuentaDTO(Cuenta cuenta);
    List<CuentaDTO> toCuentasDTO(List<Cuenta> cuentaList);

    @InheritInverseConfiguration
    @Mapping(target = "empleado", ignore = true)
    Cuenta toCuenta (CuentaDTO cuentaDTO);
    List<Cuenta> toCuentas (List<CuentaDTO> cuentaDTOList);
}
