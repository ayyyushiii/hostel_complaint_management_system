package com.hostel.category;

import com.hostel.model.Complaint;

/**
 * Extension point for complaint categories.
 */
public interface ComplaintCategory {
    String getCode();

    String getDisplayName();

    default Complaint createComplaint(int studentId, String description, String roomNumber) {
        return new Complaint(studentId, getCode(), description, roomNumber);
    }
}
