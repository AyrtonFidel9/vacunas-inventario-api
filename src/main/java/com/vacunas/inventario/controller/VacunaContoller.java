package com.vacunas.inventario.controller;

import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.dto.VacunaDTO;
import com.vacunas.inventario.services.VacunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vacunas")
@CrossOrigin(origins = "*")
public class VacunaContoller {
    @Autowired
    private VacunaService vacunaService;

    //obtener vacunas de un empleado
    /*@GetMapping("/{id}")
    public List<> obtenerVacunasPorEmpleado(@PathVariable(name="id") int id){
        return ResponseEntity.ok(empleadoService.verInformacion(id));
    }
    */
    //registrar una vacuna para un empleado
    /*@PostMapping
    public EntityResponse<VacunaDTO> ingresarVacuna(@RequestBody @Valid VacunaDTO vacunaDTO){
        return ResponseEntity<>(vacunaService.registrarInfoVacuna(vacunaDTO), HttpStatus.CREATED);
    }*/

}
