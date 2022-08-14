package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.VacunaDTO;
import com.vacunas.inventario.entity.Vacuna;
import com.vacunas.inventario.exceptions.ResourceNotFoundException;
import com.vacunas.inventario.mapper.iVacunaMapper;
import com.vacunas.inventario.repository.iVacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaService implements iVacunaService{

    @Autowired
    private iVacunaRepository vacunaRepository;

    @Autowired
    private iVacunaMapper vacunaMapper;

    @Override
    public VacunaDTO verInfoVacuna(int idVacuna) {
        Vacuna vacunaSearch = vacunaRepository.findById(idVacuna).orElseThrow(
                ()->new ResourceNotFoundException("Vacuna","id",idVacuna)
        );
        return vacunaMapper.toVacunaDTO(vacunaSearch);
    }

    @Override
    public VacunaDTO actualizarInfoVacuna(int idVacuna, VacunaDTO vacunaDTO) {
        verInfoVacuna(idVacuna);
        return registrarInfoVacuna(vacunaDTO);
    }

    @Override
    public VacunaDTO registrarInfoVacuna(VacunaDTO vacunaDTO) {
        Vacuna newVacuna = vacunaMapper.toVacuna(vacunaDTO);
        return vacunaMapper.toVacunaDTO(vacunaRepository.save(newVacuna));
    }
}
