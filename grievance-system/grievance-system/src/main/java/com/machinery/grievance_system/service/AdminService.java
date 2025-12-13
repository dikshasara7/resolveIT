package com.machinery.grievance_system.service;

import com.machinery.grievance_system.dao.UserDao;
import com.machinery.grievance_system.dao.StaffDao;
import com.machinery.grievance_system.dao.ComplaintDao;
import com.machinery.grievance_system.model.User;
import com.machinery.grievance_system.model.Staff;
import com.machinery.grievance_system.model.Complaint;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final UserDao userDao;
    private final StaffDao staffDao;
    private final ComplaintDao complaintDao;

    public AdminService(UserDao userDao, StaffDao staffDao, ComplaintDao complaintDao) {
        this.userDao = userDao;
        this.staffDao = staffDao;
        this.complaintDao = complaintDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public boolean deactivateUser(int userId) {
        return userDao.deactivateUser(userId);
    }

    public boolean deleteUser(int userId) {
        return userDao.delete(userId);
    }

    public boolean promoteToStaff(int userId, int departmentId) {

        User user = userDao.findById(userId);

        if (user == null) {
            return false;
        }

        user.setRole("STAFF");
        boolean updated = userDao.update(user);

        if (!updated) {
            return false;
        }

        return staffDao.createStaff(userId, departmentId);
    }

    public List<Complaint> getAllComplaints() {
        return complaintDao.getAll();
    }

    public List<Staff> getAllStaff() {
        return staffDao.getAllStaff();
    }

    public Map<String, Object> getDashboardStats() {

        List<User> users = userDao.getAll();
        List<Staff> staff = staffDao.getAllStaff();
        List<Complaint> complaints = complaintDao.getAll();

        int totalUsers = users.size();
        int totalStaff = staff.size();
        int totalComplaints = complaints.size();

        int resolved = 0;
        int pending = 0;
        int inProgress = 0;

        for (Complaint c : complaints) {

            if (c.getStatus() == null) continue;

            String status = c.getStatus().toUpperCase();

            if (status.equals("RESOLVED")) resolved++;
            else if (status.equals("PENDING")) pending++;
            else if (status.equals("IN_PROGRESS")) inProgress++;
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", totalUsers);
        stats.put("totalStaff", totalStaff);
        stats.put("totalComplaints", totalComplaints);
        stats.put("resolvedComplaints", resolved);
        stats.put("pendingComplaints", pending);
        stats.put("inProgressComplaints", inProgress);

        return stats;
    }
}

