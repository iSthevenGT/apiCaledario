package com.software.calendario.presentation.crontrollers;


import com.software.calendario.application.services.CalendarioProcessorService;
import com.software.calendario.domain.entities.Calendario;
import com.software.calendario.domain.services.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    @Autowired
    private CalendarioProcessorService calendarioProcessorService;

    @Autowired
    private CalendarioService calendarioService;

    @PostMapping("/generar/{anio}")
    public ResponseEntity<Boolean> generarCalendario(@PathVariable int anio) {
        boolean resultado = calendarioProcessorService.processCalendar(anio);
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/listar/{anio}")
    public ResponseEntity<List<Calendario>> listarCalendario(@PathVariable int anio) {
        List<Calendario> calendarios = calendarioService.getAll(anio);
        return ResponseEntity.ok(calendarios);
    }
}
