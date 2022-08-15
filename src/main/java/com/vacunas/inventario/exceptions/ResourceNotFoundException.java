package com.vacunas.inventario.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String nombreRecurso;

    private String nombreCampo;

    private int valorCampo;

    private String valorCadena;

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, int valorCampo) {
        super(String.format("%s no encontrado con %s : '%s'",nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, String valorCadena) {
        super(String.format("%s no encontrado con %s : '%s'",nombreRecurso, nombreCampo, valorCadena));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCadena = valorCadena;
    }
}
