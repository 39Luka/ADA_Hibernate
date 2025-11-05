package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.hibernate.UserDaoImpl;

public class UserService {
    UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }
}
