package com.vacunas.inventario.repository;

import com.vacunas.inventario.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface iCuentaRepository extends CrudRepository<Cuenta, String> {
}
