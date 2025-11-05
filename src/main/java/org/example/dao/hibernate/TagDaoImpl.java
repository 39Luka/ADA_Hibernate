package org.example.dao.hibernate;

import org.example.Actividad1.Tag;
import org.example.dao.TagDao;

public class TagDaoImpl extends GenericDaoImpl<Tag,Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }
}
