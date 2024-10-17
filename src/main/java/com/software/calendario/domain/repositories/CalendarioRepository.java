package com.software.calendario.domain.repositories;

import com.software.calendario.domain.entities.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    @Query("SELECT c FROM Calendario c WHERE YEAR(c.fecha) = :year")
    List<Calendario> findAllByYear(@Param("year") int year);

    boolean existsByFecha(LocalDate fecha);
}

