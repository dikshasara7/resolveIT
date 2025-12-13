package com.machinery.grievance_system.controller;


import com.machinery.grievance_system.model.Complaint;
import com.machinery.grievance_system.model.Staff;
import com.machinery.grievance_system.model.dto.ApiResponse;
import com.machinery.grievance_system.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{userId}")
    public ApiResponse getStaffDetails(@PathVariable int userId) {
        Staff s = staffService.getStaffByUserId(userId);
        return s == null
                ? new ApiResponse(false, "Staff not found", null)
                : new ApiResponse(true, "Staff details fetched", s);
    }

    @GetMapping("/{staffId}/complaints")
    public ApiResponse getAssignedComplaints(@PathVariable int staffId) {
        List<Complaint> list = staffService.getAssignedComplaints(staffId);
        return new ApiResponse(true, "Assigned complaints fetched", list);
    }

    @GetMapping("/all")
    public ApiResponse getAllStaff() {
        return new ApiResponse(true, "All staff fetched", staffService.getAllStaff());
    }
}