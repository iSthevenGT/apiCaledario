package com.software.calendario.domain.services;

import com.software.calendario.domain.entities.Tipo;
import com.software.calendario.domain.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    private String normalize(String text) {
        if (text == null) {
            return "";
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase().trim();
    }

    public Tipo getTipoByTipo(String tipo) {
        String normalizedTipo = normalize(tipo);
        return tipoRepository.findAll().stream()
                .filter(t -> normalize(t.getTipo()).equals(normalizedTipo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo: " + tipo));
    }
}



