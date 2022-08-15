package com.vacunas.inventario.services;

import com.vacunas.inventario.dto.JwtResponseDTO;

public interface iAuthService {
    JwtResponseDTO login(String clientId, String clientSecret);
}
