package com.vacunas.inventario.services;

import com.vacunas.inventario.entity.Cuenta;
import com.vacunas.inventario.exceptions.GenericExceptions;
import com.vacunas.inventario.exceptions.ResourceNotFoundException;
import com.vacunas.inventario.repository.iCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private iCuentaRepository cuentaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cuenta cuenta = cuentaRepository.findById(username).orElseThrow(
                ()->new ResourceNotFoundException("Cuenta","Username",username));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(cuenta.getRol().name()));
        return new User(cuenta.getNombreUsuario(),cuenta.getPassword(), roles);
    }
}
