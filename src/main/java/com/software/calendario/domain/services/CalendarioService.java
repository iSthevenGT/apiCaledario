package com.software.calendario.domain.services;

import com.software.calendario.domain.entities.Calendario;
import com.software.calendario.domain.repositories.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    public List<Calendario> getAll(int year) {
        return calendarioRepository.findAllByYear(year);
    }

    public void saveAll(List<Calendario> calendarios) {
        List<Calendario> nuevosCalendarios = calendarios.stream()
                .filter(c -> !calendarioRepository.existsByFecha(c.getFecha()))
                .collect(Collectors.toList());

        if (!nuevosCalendarios.isEmpty()) {
            calendarioRepository.saveAll(nuevosCalendarios);
        }
    }
}

