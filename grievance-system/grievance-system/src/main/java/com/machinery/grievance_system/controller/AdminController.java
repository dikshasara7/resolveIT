package com.machinery.grievance_system.controller;


import com.machinery.grievance_system.model.dto.ApiResponse;
import com.machinery.grievance_system.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ApiResponse getAllUsers() {
        return new ApiResponse(true, "All users fetched", adminService.getAllUsers());
    }

    @PutMapping("/promote/{userId}")
    public ApiResponse promoteUser(@PathVariable int userId,
                                   @RequestParam int departmentId) {
        boolean updated = adminService.promoteToStaff(userId, departmentId);
        return updated
                ? new ApiResponse(true, "User promoted to STAFF", null)
                : new ApiResponse(false, "Promotion failed", null);
    }

    @PutMapping("/deactivate/{userId}")
    public ApiResponse deactivateUser(@PathVariable int userId) {
        boolean updated = adminService.deactivateUser(userId);
        return updated
                ? new ApiResponse(true, "User deactivated", null)
                : new ApiResponse(false, "Deactivation failed", null);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse deleteUser(@PathVariable int userId) {
        boolean deleted = adminService.deleteUser(userId);
        return deleted
                ? new ApiResponse(true, "User deleted", null)
                : new ApiResponse(false, "Delete failed", null);
    }

    @GetMapping("/complaints")
    public ApiResponse getAllComplaints() {
        return new ApiResponse(true, "All complaints fetched", adminService.getAllComplaints());
    }

    @GetMapping("/staff")
    public ApiResponse getAllStaff() {
        return new ApiResponse(true, "All staff fetched", adminService.getAllStaff());
    }

    @GetMapping("/stats")
    public ApiResponse getDashboardStats() {
        return new ApiResponse(true, "Dashboard stats fetched", adminService.getDashboardStats());
    }
}