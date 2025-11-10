package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Tag;
import org.example.Actividad1.dao.TagDao;
import org.example.Actividad1.dto.TagUseDto;
import org.hibernate.Session;

import java.util.List;

public class TagDaoImpl extends GenericDaoImpl<Tag,Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }

    @Override
    public List<TagUseDto> tagsByUse(Session session) {
        String query = "select new org.example.Actividad1.dto.TagUseDto(t.name, count(s)) " +
                "from Tag t " +
                "join t.spaces s " +
                "group by t.name " +
                "order by count(s) desc";

        return session.createQuery(query, TagUseDto.class)
                .getResultList();
    }

    @Override
    public List<Tag> tagQueComiencenPor(Session session, String comienzo) {
        String query = "select t from Tag t where lower(t.name) like :comienzo";

        return session.createQuery(query,Tag.class)
                .setParameter("comienzo", comienzo.toLowerCase() + "%")
                .getResultList();
    }
}
