package com.vacunas.inventario.validator;

import com.vacunas.inventario.exceptions.ApiUnauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {
    private static final String CLIENT_CREDENTIALS="client_credentials";

    public void validate(
            MultiValueMap<String,String> paramMap,
            String grantType
    ) throws ApiUnauthorized{
        if(grantType.isEmpty()|| !grantType.equals(CLIENT_CREDENTIALS)){
            message("El campo grant_type es invalido");
        }

        if(     Objects.isNull(paramMap)||
                paramMap.getFirst("client_id").isEmpty() ||
                paramMap.getFirst("client_secret").isEmpty()
        ){
            message("client_id o client_secret son inválidos");
        }
    }

    private void message(String message) throws ApiUnauthorized{
        throw new ApiUnauthorized(message);
    }
}
