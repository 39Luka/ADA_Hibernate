package org.example.Actividad1.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.dao.TagDao;
import org.example.Actividad1.dao.hibernate.SpaceDaoImpl;
import org.example.Actividad1.dao.hibernate.TagDaoImpl;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.domain.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Set;

public class TagService {
    private final SessionFactory sf;
    private final SpaceDao spaceDao;
    private final TagDao tagDao;

    public TagService(SessionFactory sf) {
        this.sf = sf;
        spaceDao = new SpaceDaoImpl();
        tagDao = new TagDaoImpl();
    }

    public void addTagToSpace(Long spaceId, Long tagId){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            Space space = spaceDao.findById(s ,spaceId);
            if (space == null) throw new PersistenceException("Space no encontrado con ID: " + spaceId);

            Tag tag = tagDao.findById(s,tagId);
            if (tag == null) throw new PersistenceException("Tag no encontrado con ID: " + tagId);

            space.getTags().add(tag);

            spaceDao.update(s, space);

            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
}
