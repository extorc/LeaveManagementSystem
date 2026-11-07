package com.company.leave;

public class Manager extends Employee {
    private static final int ANNUAL_LEAVE_ALLOWANCE = 30;
    private static final int MAX_CONSECUTIVE_DAYS = 15;

    public Manager(int id, String name, String department) {
        super(id, name, department, ANNUAL_LEAVE_ALLOWANCE);
    }

    @Override
    public String getEmployeeType() { return "Manager"; }

    @Override
    public int getMaximumConsecutiveLeaveDays() { return MAX_CONSECUTIVE_DAYS; }

    // Managers receive an additional benefit: their initial allowance is 30 days.
}
