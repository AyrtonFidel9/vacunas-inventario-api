package com.vacunas.inventario.mapper;

import com.vacunas.inventario.dto.VacunaDTO;
import com.vacunas.inventario.entity.Vacuna;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface iVacunaMapper {

    @Mappings({
            @Mapping(source="idVacuna",target="idVacuna"),
            @Mapping(source="tipo",target="tipo"),
            @Mapping(source="fecha",target="fecha"),
            @Mapping(source="numDosis",target="numDosis"),
            @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    })
    VacunaDTO toVacunaDTO(Vacuna vacuna);
    List<VacunaDTO> toVacunasDTO(List<Vacuna> vacunaList);

    @InheritInverseConfiguration
    Vacuna toVacuna(VacunaDTO vacunaDTO);
    List<Vacuna> toVacunas(List<VacunaDTO> vacunaDTOList);

}
