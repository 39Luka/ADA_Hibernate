package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.TagDao;
import org.example.Actividad2.domain.Tag;

public class TagDaoImpl extends GenericDaoImpl<Tag,Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }
}
