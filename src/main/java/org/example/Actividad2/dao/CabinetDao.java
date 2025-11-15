package org.example.Actividad2.dao;

import org.example.Actividad2.domain.Cabinet;
import org.example.Actividad2.dto.CabinetPorGeneroDto;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface CabinetDao extends GenericDao<Cabinet,Long> {

public List<CabinetPorGeneroDto> cabinetPorGenero(Session s);

public List<Cabinet> sinPartidaDesde(Session s, LocalDateTime fecha);
}
