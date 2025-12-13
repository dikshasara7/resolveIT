package com.machinery.grievance_system.service;

import com.machinery.grievance_system.dao.ComplaintDao;
import com.machinery.grievance_system.dao.UserDao;
import com.machinery.grievance_system.model.Complaint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintDao complaintDao;
    private final UserDao userDao;

    public ComplaintService(ComplaintDao complaintDao, UserDao userDao) {
        this.complaintDao = complaintDao;
        this.userDao = userDao;
    }

    public boolean createComplaint(Complaint complaint) {
        return complaintDao.createComplaint(complaint);
    }

    public Complaint getComplaint(int id) {
        return complaintDao.getById(id);
    }

    public List<Complaint> getUserComplaints(int userId) {
        return complaintDao.getByUserId(userId);
    }

    public boolean updateStatus(int complaintId, String status) {
        return complaintDao.updateStatus(complaintId, status);
    }

    public boolean updatePriority(int complaintId, String priority) {
        return complaintDao.updatePriority(complaintId, priority);
    }

    public List<Complaint> getComplaintsForStaff(int staffId) {
        return complaintDao.getByStaffId(staffId);
    }

    public List<Complaint> getAllComplaints() {
        return complaintDao.getAll();
    }
}

