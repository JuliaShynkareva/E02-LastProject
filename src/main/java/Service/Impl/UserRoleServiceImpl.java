package Service.Impl;

import DAO.UserRoleDAO;
import Entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleDAO dao;

    @Override
    public void createUserRole(UserRoleEntity user) {
        dao.createUserRole(user);
    }
}
