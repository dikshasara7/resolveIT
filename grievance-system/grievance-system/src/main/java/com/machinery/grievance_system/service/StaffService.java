package com.machinery.grievance_system.service;

import com.machinery.grievance_system.dao.StaffDao;
import com.machinery.grievance_system.dao.ComplaintDao;
import com.machinery.grievance_system.model.Staff;
import com.machinery.grievance_system.model.Complaint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffDao staffDao;
    private final ComplaintDao complaintDao;

    public StaffService(StaffDao staffDao, ComplaintDao complaintDao) {
        this.staffDao = staffDao;
        this.complaintDao = complaintDao;
    }

    public Staff getStaffByUserId(int userId) {
        return staffDao.findByUserId(userId);
    }

    public List<Staff> getAllStaff() {
        return staffDao.getAllStaff();
    }

    public List<Complaint> getAssignedComplaints(int staffId) {
        return complaintDao.getByStaffId(staffId);
    }
}
