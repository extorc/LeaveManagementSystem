package com.company.leave;

public class Intern extends Employee {
    private static final int LEAVE_ALLOWANCE = 5;
    private static final int MAX_CONSECUTIVE_DAYS = 2;

    public Intern(int id, String name, String department) {
        super(id, name, department, LEAVE_ALLOWANCE);
    }

    @Override
    public String getEmployeeType() { return "Intern"; }

    @Override
    public int getMaximumConsecutiveLeaveDays() { return MAX_CONSECUTIVE_DAYS; }
}
