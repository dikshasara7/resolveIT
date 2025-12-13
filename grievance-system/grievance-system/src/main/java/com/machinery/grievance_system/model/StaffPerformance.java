package com.machinery.grievance_system.model;

public class StaffPerformance {
    private int perfId;
    private int staffId;
    private String monthYear;
    private int totalAssigned;
    private int totalResolved;

    public int getPerfId() {
        return perfId;
    }

    public void setPerfId(int perfId) {
        this.perfId = perfId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public int getTotalAssigned() {
        return totalAssigned;
    }

    public void setTotalAssigned(int totalAssigned) {
        this.totalAssigned = totalAssigned;
    }

    public int getTotalResolved() {
        return totalResolved;
    }

    public void setTotalResolved(int totalResolved) {
        this.totalResolved = totalResolved;
    }
}
