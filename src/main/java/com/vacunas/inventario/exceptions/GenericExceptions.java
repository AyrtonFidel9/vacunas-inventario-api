package com.vacunas.inventario.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericExceptions extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public GenericExceptions(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public GenericExceptions(HttpStatus estado, String mensaje, String mensajeAlt) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensajeAlt;
    }
}
