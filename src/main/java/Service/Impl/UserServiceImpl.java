package Service.Impl;

import DAO.UserDAO;
import Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO dao;

    @Override
    public void createUser(UserEntity client) {
        dao.createUser(client);
    }

    @Override
    public boolean isLoginExists(String login) {
        return dao.isLoginExists(login);
    }
}
