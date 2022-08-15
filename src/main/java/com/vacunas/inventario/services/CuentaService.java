package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.CuentaDTO;
import com.vacunas.inventario.entity.Cuenta;
import com.vacunas.inventario.entity.Empleado;
import com.vacunas.inventario.entity.RolCuenta;
import com.vacunas.inventario.exceptions.ResourceNotFoundException;
import com.vacunas.inventario.mapper.iCuentaMapper;
import com.vacunas.inventario.repository.iCuentaRepository;
import com.vacunas.inventario.repository.iEmpleadoRepository;
import com.vacunas.inventario.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CuentaService implements iCuentaService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private iEmpleadoRepository empleadoRepository;

    @Autowired
    private iCuentaMapper cuentaMapper;

    @Autowired
    private iCuentaRepository cuentaRepository;

    @Override
    public CuentaDTO generarCredenciales(int idEmpleado) {

        Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow(
                ()->new ResourceNotFoundException("Empleado","id",idEmpleado)
        );

        List<String> nombres = Arrays.asList(empleado.getNombres().split(" "));

        String nombreUsuario = "";

        for(String nombre : nombres){
            nombreUsuario += nombre;
        }
        Random random = new Random();
        int numero = random.nextInt (100);

        nombreUsuario += numero+"";

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setPassword(passwordEncoder.encode(generarPassword()));
        cuentaDTO.setRol(RolCuenta.EMPLEADO);
        cuentaDTO.setNombreUsuario(nombreUsuario);

        Cuenta newCuenta = cuentaMapper.toCuenta(cuentaDTO);

        newCuenta.setEmpleado(empleado);

        return cuentaMapper.toCuentaDTO(cuentaRepository.save(newCuenta));
    }

    @Override
    public CuentaDTO actualizarInformacion(String nombreUsuario, CuentaDTO cuentaDTO) {
        buscarCuenta(nombreUsuario);
        return cuentaMapper.toCuentaDTO(cuentaRepository.save(cuentaMapper.toCuenta(cuentaDTO)));
    }

    @Override
    public CuentaDTO buscarCuenta(String nombreUsuario) {

        Cuenta cuenta = cuentaRepository.findById(nombreUsuario).orElseThrow(
                ()->new ResourceNotFoundException("Cuenta","Nombre de Usuario",nombreUsuario)
        );

        return cuentaMapper.toCuentaDTO(cuenta);
    }

    @Override
    public void eliminarCuenta(String nombreUsuario) {
        cuentaRepository.delete(cuentaMapper.toCuenta(buscarCuenta(nombreUsuario)));
    }

    private String generarPassword(){
        String[] symbols = {"0", "1", "-", "*", "%", "$", "a", "b", "c"};
        int length = 10;
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(length);
            int i;
            for (i = 0; i < length; i++) {
                int indexRandom = random.nextInt ( symbols.length );
                sb.append( symbols[indexRandom] );
            }
            String password = sb.toString();
            return password;
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
        return null;
    }
}
