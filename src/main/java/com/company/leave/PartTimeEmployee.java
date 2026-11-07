package com.company.leave;

public class PartTimeEmployee extends Employee {
    private static final int ANNUAL_LEAVE_ALLOWANCE = 10;
    private static final int MAX_CONSECUTIVE_DAYS = 5;

    public PartTimeEmployee(int id, String name, String department) {
        super(id, name, department, ANNUAL_LEAVE_ALLOWANCE);
    }

    @Override
    public String getEmployeeType() { return "Part-Time"; }

    @Override
    public int getMaximumConsecutiveLeaveDays() { return MAX_CONSECUTIVE_DAYS; }
}
