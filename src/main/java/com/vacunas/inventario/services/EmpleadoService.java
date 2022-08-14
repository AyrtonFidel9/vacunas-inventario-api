package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.EmpleadoDTO;
import com.vacunas.inventario.dto.RegistrarEmpleadoDTO;
import com.vacunas.inventario.exceptions.GenericExceptions;
import com.vacunas.inventario.exceptions.ResourceNotFoundException;
import com.vacunas.inventario.mapper.iRegistrarEmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.vacunas.inventario.repository.iEmpleadoRepository;
import com.vacunas.inventario.mapper.iEmpleadoMapper;
import com.vacunas.inventario.entity.Empleado;

@Service
public class EmpleadoService implements iEmpleadoService{

    @Autowired
    private iEmpleadoRepository empleadoRepository;

    @Autowired
    private ValidacionesService validacionesService;

    @Autowired
    private iEmpleadoMapper empleadoMapper;

    @Autowired
    private iRegistrarEmpleadoMapper registrarEmpleadoMapper;

    //Busca la informacion de un empleado y retorna toda la información
    @Override
    public EmpleadoDTO verInformacion(int idEmpleado) {

        Optional<Empleado> empleadoSearch = empleadoRepository.findById(idEmpleado);
        return empleadoMapper.toEmpleadoDTO(empleadoSearch
                .orElseThrow(()->
                        new ResourceNotFoundException("Empleado","id",idEmpleado)));
    }

    @Override
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoMapper.toEmpleadosDTO((List<Empleado>) empleadoRepository.findAll());
    }

    @Override
    public void eliminarEmpleado(int idEmpleado) {
        Empleado empleadoSearch =
                empleadoRepository.findById(idEmpleado).orElseThrow(
                        ()->new ResourceNotFoundException("Empleado","id",idEmpleado)
                );

        empleadoRepository.delete(empleadoSearch);
    }

    @Override
    public List<EmpleadoDTO> filtrarPorTipoVacuna(String tipoVacuna) {

        List<Empleado> empleadosList = empleadoRepository.findEmpleadosByTipoVacuna(tipoVacuna);
        return empleadoMapper.toEmpleadosDTO(empleadosList);
    }

    @Override
    public RegistrarEmpleadoDTO registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO) {
        if(!validacionesService.cedulaValida(registrarEmpleadoDTO.getCedula())){
            throw new GenericExceptions(HttpStatus.BAD_REQUEST,
                    "Cédula nó valida, verifique que sea original y que contenga 10 dígitos");
        }

        Empleado nuevoEmpleado = registrarEmpleadoMapper.toEmpleadoRegistrado(registrarEmpleadoDTO);
        return registrarEmpleadoMapper.toRegistrarEmpleadoDTO(empleadoRepository.save(nuevoEmpleado));
    }

    @Override
    public EmpleadoDTO ingresarEmpleado(EmpleadoDTO empleadoDTO) {

        if(!validacionesService.cedulaValida(empleadoDTO.getCedula())){
            throw new GenericExceptions(HttpStatus.BAD_REQUEST,
                    "Cédula nó valida, verifique que sea original y que contenga 10 dígitos");
        }

        Empleado nuevoEmpleado = empleadoMapper.toEmpleado(empleadoDTO);
        return empleadoMapper.toEmpleadoDTO(empleadoRepository.save(nuevoEmpleado));
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(int idEmpleado, EmpleadoDTO empleadoDTO) {
        verInformacion(idEmpleado);
        return ingresarEmpleado(empleadoDTO);
    }

    @Override
    public List<EmpleadoDTO> filtrarPorEstadoVacuna(boolean estadoVacuna) {
        List<Empleado> empleadosList = empleadoRepository.findEmpleadosByEstadoVacuna(estadoVacuna);
        return empleadoMapper.toEmpleadosDTO(empleadosList);
    }

    @Override
    public List<EmpleadoDTO> filtrarPorRangoFechaVacuna(LocalDate fInicio, LocalDate fFin) {
        List<Empleado> empleadosList = empleadoRepository.findEmpleadosByRangoFecha(fInicio,fFin);
        return empleadoMapper.toEmpleadosDTO(empleadosList);
    }
}
