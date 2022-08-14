package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.VacunaDTO;

public interface iVacunaService{
    VacunaDTO verInfoVacuna(int idVacuna);

    VacunaDTO actualizarInfoVacuna(int idVacuna, VacunaDTO vacunaDTO);

    VacunaDTO registrarInfoVacuna(VacunaDTO vacunaDTO);
}
