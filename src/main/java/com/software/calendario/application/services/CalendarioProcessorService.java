package com.software.calendario.application.services;


import com.software.calendario.domain.entities.Calendario;
import com.software.calendario.domain.services.CalendarioService;
import com.software.calendario.infraestructure.services.CalendarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CalendarioProcessorService {

    @Autowired
    private CalendarioClient calendarioClient;

    @Autowired
    private CalendarioService calendarioService;

    public boolean processCalendar(int year) {
        List<Calendario> calendarios = calendarioClient.obtenerCalendario(year);
        if (calendarios.isEmpty()) {
            return false;
        }
        calendarioService.saveAll(calendarios);
        return true;
    }

}


