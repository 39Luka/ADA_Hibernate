package org.example.Actividad2.dao;

import org.example.Actividad2.dto.ArcadeIncomeDto;
import org.example.Actividad2.domain.Arcade;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface ArcadeDao extends GenericDao<Arcade,Long> {

     List<Arcade> arcadesPorNombre(Session session, String name);

     List<ArcadeIncomeDto> ingresosEstimadosArcades(Session session, LocalDateTime start, LocalDateTime end);
}
