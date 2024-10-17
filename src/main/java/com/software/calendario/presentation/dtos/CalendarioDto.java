package com.software.calendario.presentation.dtos;

import lombok.Data;

@Data
public class CalendarioDto {
    private String fecha;       // Formato 'yyyy-MM-dd'
    private String tipo;
    private String descripcion;

    // Getters y setters
}
