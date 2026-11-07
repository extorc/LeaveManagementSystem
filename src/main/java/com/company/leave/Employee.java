package com.company.leave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Common employee data and leave operations.
 * Subclasses provide their own leave allowance and consecutive-leave rule.
 */
public abstract class Employee {
    private final int employeeId;
    private final String employeeName;
    private final String department;
    private int leaveBalance;
    private int nextRequestId = 1;
    private final List<LeaveRequest> leaveRequests = new ArrayList<>();

    protected Employee(int employeeId, String employeeName, String department, int leaveBalance) {
        if (employeeId <= 0 || leaveBalance < 0) {
            throw new IllegalArgumentException("Employee ID must be positive and balance cannot be negative.");
        }
        this.employeeId = employeeId;
        this.employeeName = requireText(employeeName, "Employee name");
        this.department = requireText(department, "Department");
        this.leaveBalance = leaveBalance;
    }

    private String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null.");
        String cleaned = value.trim();
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return cleaned;
    }

    public abstract String getEmployeeType();

    /** Maximum number of consecutive days allowed in one request. */
    public abstract int getMaximumConsecutiveLeaveDays();

    // Method overloading: convenience method with a default reason.
    public LeaveRequest applyLeave(int days) {
        return applyLeave(days, "Personal");
    }

    public LeaveRequest applyLeave(int days, String reason) {
        if (days <= 0) {
            throw new IllegalArgumentException("Leave days must be greater than zero.");
        }
        if (days > getMaximumConsecutiveLeaveDays()) {
            throw new IllegalArgumentException(getEmployeeType() + " employees can request at most "
                    + getMaximumConsecutiveLeaveDays() + " consecutive day(s).");
        }
        if (days > leaveBalance) {
            throw new IllegalArgumentException("Insufficient leave balance. Available: " + leaveBalance);
        }

        LeaveRequest request = new LeaveRequest(nextRequestId++, days, reason);
        leaveBalance -= days;
        leaveRequests.add(request);
        return request;
    }

    public void cancelLeave(int requestId) {
        LeaveRequest request = leaveRequests.stream()
                .filter(item -> item.getRequestId() == requestId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No leave request found with ID " + requestId));

        if (request.getStatus() == LeaveStatus.CANCELLED) {
            throw new IllegalStateException("This leave request is already cancelled.");
        }

        request.markCancelled();
        leaveBalance += request.getDays();
    }

    // Overloaded cancellation method: accepts the request object directly.
    public void cancelLeave(LeaveRequest request) {
        Objects.requireNonNull(request, "Leave request cannot be null.");
        cancelLeave(request.getRequestId());
    }

    public void checkLeaveBalance() {
        System.out.println(employeeName + " | Leave balance: " + leaveBalance + " day(s)");
    }

    public void displayDetails() {
        System.out.println("Employee ID   : " + employeeId);
        System.out.println("Name          : " + employeeName);
        System.out.println("Department    : " + department);
        System.out.println("Employee type : " + getEmployeeType());
        System.out.println("Leave balance : " + leaveBalance + " day(s)");
        System.out.println("Max/request   : " + getMaximumConsecutiveLeaveDays() + " day(s)");
    }

    public void displayLeaveHistory() {
        System.out.println("Leave history for " + employeeName + ":");
        if (leaveRequests.isEmpty()) {
            System.out.println("  No leave requests.");
            return;
        }
        leaveRequests.forEach(request -> System.out.println("  " + request));
    }

    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDepartment() { return department; }
    public int getLeaveBalance() { return leaveBalance; }
    public List<LeaveRequest> getLeaveRequests() {
        return Collections.unmodifiableList(leaveRequests);
    }
}
