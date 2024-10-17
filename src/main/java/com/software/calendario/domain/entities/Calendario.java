package com.software.calendario.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Calendario")
@Data
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Fecha", nullable = false, unique = true)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "IdTipo", nullable = false)
    private Tipo tipo;

    @Column(name = "Descripcion")
    private String descripcion;

    // Constructores, getters y setters
}
