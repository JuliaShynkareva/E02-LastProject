package DAO;

import Entity.UserEntity;

public interface UserDAO {

    void createUser(UserEntity client);
    boolean isLoginExists(String login);
}
