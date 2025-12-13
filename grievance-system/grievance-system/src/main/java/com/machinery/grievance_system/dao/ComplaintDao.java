package com.machinery.grievance_system.dao;

import com.machinery.grievance_system.model.Complaint;
import java.util.List;

public interface ComplaintDao {
    boolean createComplaint(Complaint complaint);
    boolean updateStatus(int complaintId, String status);
    boolean updatePriority(int complaintId, String priority);
    Complaint getById(int complaintId);

    List<Complaint> getByUserId(int userId);
    List<Complaint> getByStaffId(int staffId);
    List<Complaint> getAll();
}