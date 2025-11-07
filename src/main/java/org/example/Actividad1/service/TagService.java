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
    public Long create(Tag tag){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            tagDao.save(s,tag);
            tx.commit();
            return tag.getId();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public Tag findById(Long id){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            Tag tag = tagDao.findById(s,id);
            tx.commit();
            return tag;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void update(Tag tag){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            tagDao.update(s,tag);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null ) tx.rollback();
            throw e;
        }
    }
    public void deleteById(Long id){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            tagDao.deleteById(s, id);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void delete(Tag tag){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            tagDao.delete(s, tag);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
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
