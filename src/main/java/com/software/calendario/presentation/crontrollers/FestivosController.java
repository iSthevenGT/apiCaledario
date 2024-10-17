package com.software.calendario.presentation.crontrollers;

import com.software.calendario.infraestructure.services.FestivoClient;
import com.software.calendario.presentation.dtos.FestivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/festivos")
public class FestivosController {

    @Autowired
    private FestivoClient festivoClient;

    @GetMapping("/obtener/{anio}")
    private List<FestivoDTO> obtenerFestivos(@PathVariable int anio) {
        return festivoClient.obtenerFestivos(anio);
    }
}
