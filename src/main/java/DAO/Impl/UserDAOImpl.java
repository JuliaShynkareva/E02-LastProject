package DAO.Impl;

import DAO.UserDAO;
import Entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public void createUser(UserEntity client) {
        sessionFactory.getCurrentSession().save(client);
    }

    @Override
    public boolean isLoginExists(String login) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM users WHERE username = :login");
        query.setParameter("login", login);
        return query.list().size() > 0;
    }
}
