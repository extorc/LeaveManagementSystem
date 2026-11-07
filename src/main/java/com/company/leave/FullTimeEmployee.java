package com.company.leave;

public class FullTimeEmployee extends Employee {
    private static final int ANNUAL_LEAVE_ALLOWANCE = 20;
    private static final int MAX_CONSECUTIVE_DAYS = 10;

    public FullTimeEmployee(int id, String name, String department) {
        super(id, name, department, ANNUAL_LEAVE_ALLOWANCE);
    }

    @Override
    public String getEmployeeType() { return "Full-Time"; }

    @Override
    public int getMaximumConsecutiveLeaveDays() { return MAX_CONSECUTIVE_DAYS; }
}
