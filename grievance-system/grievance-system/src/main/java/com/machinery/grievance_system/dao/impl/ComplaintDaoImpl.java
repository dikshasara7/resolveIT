package com.machinery.grievance_system.dao.impl;

import com.machinery.grievance_system.dao.ComplaintDao;
import com.machinery.grievance_system.model.Complaint;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ComplaintDaoImpl implements ComplaintDao {

    private final JdbcTemplate jdbcTemplate;

    public ComplaintDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Complaint> rowMapper = new RowMapper<Complaint>() {
        @Override
        public Complaint mapRow(ResultSet rs, int rowNum) throws SQLException {
            Complaint c = new Complaint();
            c.setComplaintId(rs.getInt("complaint_id"));
            c.setUserId(rs.getInt("user_id"));

            int staffId = rs.getInt("staff_id");
            c.setStaffId(rs.wasNull() ? null : staffId);

            c.setDepartmentId(rs.getInt("department_id"));
            c.setSubject(rs.getString("subject"));
            c.setDescription(rs.getString("description"));
            c.setStatus(rs.getString("status"));
            c.setPriority(rs.getString("priority"));

            Timestamp ts = rs.getTimestamp("created_at");
            if (ts != null) {
                c.setCreatedAt(ts.toLocalDateTime());
            }

            return c;
        }
    };

    @Override
    public boolean createComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaints1 (user_id, staff_id, department_id, subject, description, status, priority) VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                complaint.getUserId(),
                complaint.getStaffId(),
                complaint.getDepartmentId(),
                complaint.getSubject(),
                complaint.getDescription(),
                complaint.getStatus(),
                complaint.getPriority()
        ) > 0;
    }

    @Override
    public boolean updateStatus(int complaintId, String status) {
        String sql = "UPDATE complaints1 SET status = ? WHERE complaint_id = ?";
        return jdbcTemplate.update(sql, status, complaintId) > 0;
    }

    @Override
    public boolean updatePriority(int complaintId, String priority) {
        String sql = "UPDATE complaints1 SET priority = ? WHERE complaint_id = ?";
        return jdbcTemplate.update(sql, priority, complaintId) > 0;
    }

    @Override
    public Complaint getById(int complaintId) {
        String sql = "SELECT * FROM complaints1 WHERE complaint_id = ?";
        List<Complaint> list = jdbcTemplate.query(sql, rowMapper, complaintId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Complaint> getByUserId(int userId) {
        String sql = "SELECT * FROM complaints1 WHERE user_id = ?";
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    @Override
    public List<Complaint> getByStaffId(int staffId) {
        String sql = "SELECT * FROM complaints1 WHERE staff_id = ?";
        return jdbcTemplate.query(sql, rowMapper, staffId);
    }

    @Override
    public List<Complaint> getAll() {
        String sql = "SELECT * FROM complaints1";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
