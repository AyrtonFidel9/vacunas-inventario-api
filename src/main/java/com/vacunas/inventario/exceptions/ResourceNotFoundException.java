package com.vacunas.inventario.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String nombreRecurso;

    private String nombreCampo;

    private int valorCampo;

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, int valorCampo) {
        super(String.format("%s no encontrado con %s : '%s'",nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }
}
