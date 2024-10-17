package com.software.calendario.infraestructure.services;

import com.software.calendario.domain.entities.Calendario;
import com.software.calendario.domain.entities.Tipo;
import com.software.calendario.domain.services.TipoService;
import com.software.calendario.presentation.dtos.CalendarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


@Service
public class CalendarioClient {

    private final RestTemplate restTemplate;
    private final TipoService tipoService;

    @Autowired
    public CalendarioClient(RestTemplate restTemplate, TipoService tipoService) {
        this.restTemplate = restTemplate;
        this.tipoService = tipoService;
    }

    public List<Calendario> obtenerCalendario(int year) {
        String url = "http://localhost:3030/api/festivos/calendario/" + year;
        CalendarioDto[] fechas = restTemplate.getForObject(url, CalendarioDto[].class);

        if (fechas == null || fechas.length == 0) {
            return Collections.emptyList();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); // Ajustado para coincidir con el formato de la API externa

        List<Calendario> calendarios = new ArrayList<>();
        for (CalendarioDto dto : fechas) {
            try {
                LocalDate fecha = LocalDate.parse(dto.getFecha(), formatter);

                Calendario calendario = new Calendario();
                calendario.setFecha(fecha);
                calendario.setDescripcion(dto.getDescripcion());

                Tipo tipo = determinarTipo(dto);
                calendario.setTipo(tipo);

                calendarios.add(calendario);
            } catch (DateTimeParseException e) {
                // Manejar la excepción y continuar
                e.printStackTrace();
                continue;
            }
        }

        return calendarios;
    }

    private String normalize(String text) {
        if (text == null) {
            return "";
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase().trim();
    }

    private Tipo determinarTipo(CalendarioDto dto) {
        String tipoApi = normalize(dto.getTipo());
        String descripcion = normalize(dto.getDescripcion());

        if ("dia festivo".equals(tipoApi)) {
            return tipoService.getTipoByTipo("Día festivo"); // ID 3
        } else if ("sabado".equals(descripcion) || "domingo".equals(descripcion)) {
            return tipoService.getTipoByTipo("Fin de Semana"); // ID 2
        } else {
            return tipoService.getTipoByTipo("Día laboral"); // ID 1
        }
    }
}