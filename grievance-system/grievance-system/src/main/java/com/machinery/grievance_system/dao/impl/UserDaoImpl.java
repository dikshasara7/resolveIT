package com.machinery.grievance_system.dao.impl;

import com.machinery.grievance_system.dao.UserDao;
import com.machinery.grievance_system.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setUserId(rs.getInt("user_id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
            u.setActive(rs.getBoolean("is_active"));
            return u;
        }
    };

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users1 WHERE email = ?";
        List<User> list = jdbcTemplate.query(sql, userRowMapper, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public User findById(int userId) {
        String sql = "SELECT * FROM users1 WHERE user_id = ?";
        List<User> list = jdbcTemplate.query(sql, userRowMapper, userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO users1 (name, email, password, role, is_active) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.isActive() ? 1 : 0
        ) > 0;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users1 SET name = ?, email = ?, password = ?, role = ?, is_active = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.isActive(),
                user.getUserId()
        ) > 0;
    }

    @Override
    public boolean deactivateUser(int userId) {
        String sql = "UPDATE users1 SET is_active = 0 WHERE user_id = ?";
        return jdbcTemplate.update(sql, userId) > 0;
    }
    @Override
    public List<User>getAll() {
        String sql = "SELECT * FROM users1";
        return jdbcTemplate.query(sql, userRowMapper);
    }
    @Override
    public boolean delete(int userId) {
        String sql = "DELETE FROM users1 WHERE user_id = ?";
        int rows = jdbcTemplate.update(sql, userId);
        return rows > 0;
    }
}



