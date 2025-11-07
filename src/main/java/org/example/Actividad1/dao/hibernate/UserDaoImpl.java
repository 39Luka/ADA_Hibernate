package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.User;
import org.example.Actividad1.dao.UserDao;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
