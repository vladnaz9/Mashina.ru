package services;

import models.User;
import repositories.UserRepositoryJdbcImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepositoryJdbcImpl userRepository;

    public UserServiceImpl(UserRepositoryJdbcImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean userIsExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
}
