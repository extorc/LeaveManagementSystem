package com.company.leave;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** Coordinates employee registration and lookup. */
public class LeaveManagementSystem {
    private final Map<Integer, Employee> employees = new LinkedHashMap<>();

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if (employees.containsKey(employee.getEmployeeId())) {
            throw new IllegalArgumentException("Employee ID already exists: " + employee.getEmployeeId());
        }
        employees.put(employee.getEmployeeId(), employee);
    }

    public Employee findEmployee(int employeeId) {
        Employee employee = employees.get(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }
        return employee;
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public void displayAllEmployees() {
        System.out.println("\n========== EMPLOYEE DIRECTORY ==========");
        for (Employee employee : employees.values()) {
            System.out.printf("%-5d %-18s %-12s %-15s %2d days%n",
                    employee.getEmployeeId(), employee.getEmployeeName(),
                    employee.getEmployeeType(), employee.getDepartment(),
                    employee.getLeaveBalance());
        }
    }
}
