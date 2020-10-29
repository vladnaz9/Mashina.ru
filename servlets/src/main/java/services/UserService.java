package services;

import models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean userIsExist(String email);

    void addUser(User user);

    User getUser(String email);

    User getUserById(Long id);

    void setImage(String filename, Long id);
}
