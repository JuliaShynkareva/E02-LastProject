package Service;

import Entity.UserEntity;

public interface UserService {

    void createUser(UserEntity client);
    boolean isLoginExists(String login);
}
