package com.machinery.grievance_system.dao.impl;

import com.machinery.grievance_system.dao.StaffDao;
import com.machinery.grievance_system.model.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaffDaoImpl implements StaffDao {

    private final JdbcTemplate jdbcTemplate;

    public StaffDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Staff> rowMapper = new RowMapper<Staff>() {
        @Override
        public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
            Staff s = new Staff();
            s.setStaffId(rs.getInt("staff_id"));
            s.setUserId(rs.getInt("user_id"));
            s.setDepartmentId(rs.getInt("department_id"));
            return s;
        }
    };

    @Override
    public Staff findByUserId(int userId) {
        String sql = "SELECT * FROM staff1 WHERE user_id = ?";
        List<Staff> list = jdbcTemplate.query(sql, rowMapper, userId);
        return list.isEmpty() ? null : list.get(0);
    }
    @Override
    public boolean createStaff(int userId, int departmentId) {
        String sql = "INSERT INTO staff1 (userId, departmentId) VALUES (?, ?)";
        int rows = jdbcTemplate.update(sql, userId, departmentId);
        return rows > 0;
    }

    @Override
    public Staff findByStaffId(int staffId) {
        String sql = "SELECT * FROM staff1 WHERE staff_id = ?";
        List<Staff> list = jdbcTemplate.query(sql, rowMapper, staffId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Staff> getAllStaff() {
        String sql = "SELECT * FROM staff1";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
