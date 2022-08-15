package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.JwtResponseDTO;
import com.vacunas.inventario.security.JwtIO;
import com.vacunas.inventario.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements iAuthService{

    @Autowired
    private JwtIO jwtIO;

    @Autowired
    private DateUtils dateUtils;

    @Value("${jms.jwt.token.expires-in}")
    private int EXPIRES_IN;

    @Override
    public JwtResponseDTO login(String clientId, String clientSecret) {
        JwtResponseDTO jwt = JwtResponseDTO.builder()
                .tokenType("bearer")
                .accessToken(jwtIO.generateToken("Hola mundo api rest"))
                .issuedAt(dateUtils.getDateMillis()+"")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return jwt;
    }
}
