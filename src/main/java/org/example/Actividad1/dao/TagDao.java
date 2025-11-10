package org.example.Actividad1.dao;

import org.example.Actividad1.domain.Tag;
import org.example.Actividad1.dto.TagUseDto;
import org.hibernate.Session;

import java.util.List;

public interface TagDao extends GenericDao<Tag, Long> {

    List<TagUseDto> tagsByUse(Session session);
    List<Tag> tagQueComiencenPor(Session session, String comienzo);

}
