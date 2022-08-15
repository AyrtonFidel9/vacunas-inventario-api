package com.vacunas.inventario.controller;

import com.vacunas.inventario.dto.CuentaDTO;
import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.dto.RegistrarEmpleadoDTO;
import com.vacunas.inventario.entity.Cuenta;
import com.vacunas.inventario.services.CuentaService;
import com.vacunas.inventario.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private CuentaService cuentaService;

    @Operation(summary = "Registra un empleado al sistema de inventario de vacunas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado ingresado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegistrarEmpleadoDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Error al ingresar un empleado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<RegistrarEmpleadoDTO> ingresarEmpleado(@RequestBody @Valid RegistrarEmpleadoDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoService.registrarEmpleado(empleadoDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina un empleado del sistema de inventario de vacunas")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable(name = "id") int id) {
        EmpleadoDTO empleadoDTO = empleadoService.verInformacion(id);
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>("Empleado con id " + id + " eliminado con exito", HttpStatus.OK);
    }

    @Operation(summary = "Actualiza los datos del empleado del sistema de inventario de vacunas")
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable(name = "id") int id,
                                                          @RequestBody @Valid EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoDTO1 = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return new ResponseEntity<>(empleadoDTO1, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los datos de un empleado por el id")
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(empleadoService.verInformacion(id));
    }


    @Operation(summary = "Lista todos los empleados sin filtros")
    @GetMapping
    public List<EmpleadoDTO> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @Operation(summary = "Lista todos los empleados por algun tipo de vacuna en específico")
    @GetMapping("/tipo-vacuna/{tipo}")
    public List<EmpleadoDTO> empleadoPorTipoVacuna(@PathVariable(name="tipo") String tipo){
        return empleadoService.filtrarPorTipoVacuna(tipo);
    }

    @Operation(summary = "Lista todos los empleados segun esten o no esten vacunados (estado de vacunacion)")
    @GetMapping("/estado-vacuna/{estado}")
    public List<EmpleadoDTO> empleadoPorEstadoVacuna(@PathVariable(name="estado") boolean estado){
        return empleadoService.filtrarPorEstadoVacuna(estado);
    }

    @Operation(summary = "Lista todos los empleados vacunados en un rango de fecha en específico")
    @GetMapping("/rango-fecha-vacuna/{inicio}/{fin}")
    public List<EmpleadoDTO> empleadoPorRangoFechaVacuna(@PathVariable(name="inicio") LocalDate inicio
        ,@PathVariable(name="fin") LocalDate fin){
        return empleadoService.filtrarPorRangoFechaVacuna(inicio, fin);
    }

    @Operation(summary = "Genera/Dar de alta la cuenta de un empleado con sus credenciales")
    @PostMapping("/{id}")
    public ResponseEntity<CuentaDTO> generarCredenciales(@PathVariable(name="id") int id){
        return ResponseEntity.ok(cuentaService.generarCredenciales(id));
    }

    @Operation(summary = "Actualiza la cuenta de un empleado")
    @PutMapping("/{usuario}")
    public ResponseEntity<CuentaDTO> actualizarCuentaEmpleado(@PathVariable(name="usuario") String usuario,
                                                          @RequestBody @Valid CuentaDTO cuentaDTO){
        CuentaDTO cuentaDTO1 = cuentaService.actualizarInformacion(usuario, cuentaDTO);
        return new ResponseEntity<>(cuentaDTO1, HttpStatus.OK);
    }

    @Operation(summary = "Elimina la cuenta de un empleado")
    @DeleteMapping("/eliminar-cuenta/{usuario}")
    public ResponseEntity<String> eliminarEmpleado (@PathVariable(name = "usuario") String usuario){
        CuentaDTO cuentaDTO = cuentaService.buscarCuenta(usuario);
        cuentaService.eliminarCuenta(usuario);
        return new ResponseEntity<>("La cuenta con usuario "+usuario+" ha sido eliminada con exito",HttpStatus.OK);
    }
}
