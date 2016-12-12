package DAO.Impl;

import DAO.UserRoleDAO;
import Entity.UserRoleEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository("userRoleDAO")
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public void createUserRole(UserRoleEntity user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
