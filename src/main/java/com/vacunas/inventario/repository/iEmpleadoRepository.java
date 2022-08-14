package com.vacunas.inventario.repository;

import com.vacunas.inventario.entity.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.query.Param;


public interface iEmpleadoRepository extends CrudRepository<Empleado, Integer> {


    @Query(value =
            "SELECT * FROM empleados " +
            "INNER JOIN vacunas" +
            "empleados.id_empleado = vacunas.id_empleado" +
                    "WHERE vacunas.tipo=:tipo", nativeQuery = true)
    List<Empleado> findEmpleadosByTipoVacuna(@Param("tipo")String tipo);

    List<Empleado> findEmpleadosByEstadoVacuna(boolean estado);

    @Query(value =
            "SELECT * FROM empleados " +
                    "INNER JOIN vacunas" +
                    "empleados.id_empleado = vacunas.id_empleado" +
                    "WHERE vacunas.fecha BETWEEN :inicio AND :fin", nativeQuery = true)
    List<Empleado> findEmpleadosByRangoFecha(@Param("inicio")LocalDate inicio, @Param("fin")LocalDate fin);

}
