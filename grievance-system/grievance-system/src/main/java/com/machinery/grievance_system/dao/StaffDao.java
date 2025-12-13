package com.machinery.grievance_system.dao;

import com.machinery.grievance_system.model.Staff;
import java.util.List;

public interface StaffDao {
    Staff findByUserId(int userId);
    Staff findByStaffId(int staffId);
    List<Staff> getAllStaff();
    boolean createStaff(int userId, int departmentId);
}
