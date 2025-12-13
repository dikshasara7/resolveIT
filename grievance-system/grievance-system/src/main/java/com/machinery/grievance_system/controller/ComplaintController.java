package com.machinery.grievance_system.controller;

import com.machinery.grievance_system.model.Complaint;
import com.machinery.grievance_system.model.dto.ApiResponse;
import com.machinery.grievance_system.service.ComplaintService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/create")
    public ApiResponse createComplaint(@RequestBody Complaint complaint) {
        boolean created = complaintService.createComplaint(complaint);
        return created
                ? new ApiResponse(true, "Complaint created successfully", complaint)
                : new ApiResponse(false, "Failed to create complaint", null);
    }

    @GetMapping("/user/{userId}")
    public ApiResponse getUserComplaints(@PathVariable int userId) {
        List<Complaint> list = complaintService.getUserComplaints(userId);
        return new ApiResponse(true, "User complaints fetched", list);
    }

    @GetMapping("/{complaintId}")
    public ApiResponse getComplaintById(@PathVariable int complaintId) {
        Complaint complaint = complaintService.getComplaint(complaintId);
        return complaint == null
                ? new ApiResponse(false, "Complaint not found", null)
                : new ApiResponse(true, "Complaint details fetched", complaint);
    }

    @GetMapping("/staff/{staffId}")
    public ApiResponse getComplaintsForStaff(@PathVariable int staffId) {
        List<Complaint> list = complaintService.getComplaintsForStaff(staffId);
        return new ApiResponse(true, "Complaints assigned to staff fetched", list);
    }

    @PutMapping("/{id}/status")
    public ApiResponse updateStatus(@PathVariable int id,
                                    @RequestParam String status) {
        boolean updated = complaintService.updateStatus(id, status);
        return updated
                ? new ApiResponse(true, "Status updated", null)
                : new ApiResponse(false, "Failed to update status", null);
    }

    @PutMapping("/{id}/priority")
    public ApiResponse updatePriority(@PathVariable int id,
                                      @RequestParam String priority) {
        boolean updated = complaintService.updatePriority(id, priority);
        return updated
                ? new ApiResponse(true, "Priority updated", null)
                : new ApiResponse(false, "Failed to update priority", null);
    }

    @GetMapping("/all")
    public ApiResponse getAllComplaints() {
        return new ApiResponse(true, "All complaints fetched",
                complaintService.getAllComplaints());
    }
}
