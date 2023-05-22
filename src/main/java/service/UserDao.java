package service;

import model.Role;
import model.User;

import java.util.List;

public interface UserDao {
    User add(User user);

    User getById(int id);

    List<User> getAllUser();

    User updateUser(User user);

    void deleteUser(User user);

    void addRoleToUser(User user, Role role);
}
