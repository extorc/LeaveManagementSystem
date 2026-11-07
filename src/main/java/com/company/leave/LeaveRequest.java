package com.company.leave;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents one leave request made by an employee.
 * A request is approved immediately in this simplified application.
 */
public class LeaveRequest {
    private final int requestId;
    private final int days;
    private final String reason;
    private final LocalDate requestDate;
    private LeaveStatus status;

    public LeaveRequest(int requestId, int days, String reason) {
        if (requestId <= 0 || days <= 0) {
            throw new IllegalArgumentException("Request ID and leave days must be positive.");
        }
        this.requestId = requestId;
        this.days = days;
        this.reason = Objects.requireNonNull(reason, "Reason cannot be null").trim();
        if (this.reason.isEmpty()) {
            throw new IllegalArgumentException("Reason cannot be empty.");
        }
        this.requestDate = LocalDate.now();
        this.status = LeaveStatus.APPROVED;
    }

    public int getRequestId() { return requestId; }
    public int getDays() { return days; }
    public String getReason() { return reason; }
    public LocalDate getRequestDate() { return requestDate; }
    public LeaveStatus getStatus() { return status; }

    public void markCancelled() {
        if (status == LeaveStatus.CANCELLED) {
            throw new IllegalStateException("This leave request is already cancelled.");
        }
        status = LeaveStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Request #" + requestId + " | " + days + " day(s) | " + reason
                + " | " + requestDate + " | " + status;
    }
}
