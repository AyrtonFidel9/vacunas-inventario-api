package com.vacunas.inventario.repository;

import com.vacunas.inventario.entity.Vacuna;
import org.springframework.data.repository.CrudRepository;

public interface iVacunaRepository extends CrudRepository<Vacuna,Integer> {
}
