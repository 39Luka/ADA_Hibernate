package org.example.Actividad1.dao;

import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dto.MostProfitSpacesDto;
import org.example.Actividad1.dto.SpaceByVenueTagDto;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;

public interface SpaceDao extends GenericDao<Space, Long> {
    List<MostProfitSpacesDto> findTop3MostProfitSpaces(Session session);

    List<Space> encontrarEspacionNuncaReservados(Session session);

    List<String> top5CiudadesMasEspacios(Session session);

    List<SpaceByVenueTagDto> spacePorVenueTag(Session session);

    List<Space> spaceElxWifi(Session session);

    List<Space> activeCapMinPrecioMax(Session session, int capacidadMin, BigDecimal precioMax);
}
