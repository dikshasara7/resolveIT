package com.machinery.grievance_system.dao;

import com.machinery.grievance_system.model.User;
import java.util.List;

public interface UserDao {
    User findByEmail(String email);
    User findById(int userId);
    boolean save(User user);
    boolean update(User user);
    boolean deactivateUser(int userId);
    List<User> getAll();
    boolean delete(int userId);
}