package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Tag;
import org.example.Actividad1.dao.TagDao;

public class TagDaoImpl extends GenericDaoImpl<Tag,Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }
}
