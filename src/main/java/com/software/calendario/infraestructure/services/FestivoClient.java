package com.software.calendario.infraestructure.services;

import com.software.calendario.presentation.dtos.FestivoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FestivoClient {

    public List<FestivoDTO> obtenerFestivos(int year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:3030/api/festivos/obtener/";
        FestivoDTO[] festivos = restTemplate.getForObject(url + year, FestivoDTO[].class);
        assert festivos != null;
        return Arrays.asList(festivos);
    }
}
