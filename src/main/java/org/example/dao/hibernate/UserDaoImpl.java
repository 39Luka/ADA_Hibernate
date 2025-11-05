package org.example.dao.hibernate;

import org.example.Actividad1.User;
import org.example.dao.UserDao;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
