package com.company.leave;

public class Main {
    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();

        // At least three employee objects; Manager demonstrates the change request.
        Employee ananya = new FullTimeEmployee(101, "Ananya Sharma", "Engineering");
        Employee rohan = new PartTimeEmployee(102, "Rohan Mehta", "Marketing");
        Employee priya = new Intern(103, "Priya Nair", "Design");
        Employee arjun = new Manager(104, "Arjun Rao", "Engineering");

        system.addEmployee(ananya);
        system.addEmployee(rohan);
        system.addEmployee(priya);
        system.addEmployee(arjun);

        system.displayAllEmployees();

        System.out.println("\n========== POLYMORPHIC EMPLOYEE DETAILS ==========");
        for (Employee employee : system.getAllEmployees()) {
            employee.displayDetails();
            System.out.println("----------------------------------------");
        }

        System.out.println("\n========== APPLY FOR LEAVE ==========");
        LeaveRequest ananyaRequest = ananya.applyLeave(3, "Family function");
        System.out.println("Approved: " + ananyaRequest);
        LeaveRequest rohanRequest = rohan.applyLeave(2); // overloaded method
        System.out.println("Approved: " + rohanRequest);
        LeaveRequest priyaRequest = priya.applyLeave(1, "Personal work");
        System.out.println("Approved: " + priyaRequest);
        LeaveRequest managerRequest = arjun.applyLeave(12, "Annual vacation");
        System.out.println("Approved: " + managerRequest);

        System.out.println("\n========== UPDATED BALANCES ==========");
        ananya.checkLeaveBalance();
        rohan.checkLeaveBalance();
        priya.checkLeaveBalance();
        arjun.checkLeaveBalance();

        System.out.println("\n========== CANCEL A REQUEST ==========");
        ananya.cancelLeave(ananyaRequest.getRequestId());
        System.out.println("Cancelled request #" + ananyaRequest.getRequestId());
        ananya.checkLeaveBalance();

        System.out.println("\n========== LEAVE HISTORY ==========");
        ananya.displayLeaveHistory();
        arjun.displayLeaveHistory();

        System.out.println("\n========== VALIDATION / EXCEPTION HANDLING ==========");
        try {
            priya.applyLeave(3, "Long vacation"); // Intern limit is 2 consecutive days.
        } catch (IllegalArgumentException exception) {
            System.out.println("Request rejected: " + exception.getMessage());
        }

        try {
            system.addEmployee(new FullTimeEmployee(101, "Duplicate", "IT"));
        } catch (IllegalArgumentException exception) {
            System.out.println("Registration rejected: " + exception.getMessage());
        }

        System.out.println("\nApplication demonstration completed.");
    }
}
