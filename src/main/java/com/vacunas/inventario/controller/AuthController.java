package com.vacunas.inventario.controller;

import com.vacunas.inventario.dto.AuthRequestDTO;
import com.vacunas.inventario.exceptions.ApiUnauthorized;
import com.vacunas.inventario.services.AuthService;
import com.vacunas.inventario.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth")

public class AuthController {

    @Autowired
    private AuthService  authService;

    @Autowired
    private AuthValidator validator;

    @PostMapping(path = "/client_credentials/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login (
            @RequestBody MultiValueMap<String,String> paramMap,
            @RequestParam("grant_type")String grantType
    ) throws ApiUnauthorized {
        validator.validate(paramMap,grantType);
        return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("Sesión iniciada", HttpStatus.OK);
    }


}
