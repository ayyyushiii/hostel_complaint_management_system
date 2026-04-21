package com.hostel.service.impl;

import com.hostel.dao.ComplaintDAO;
import com.hostel.service.ComplaintModifier;
import java.util.Map;

/**
 * Modifier to assign a complaint to a staff member.
 * Expected params:
 * - "staffId": Integer (required)
 */
public class AssignToStaffModifier implements ComplaintModifier {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    public boolean apply(int complaintId, int actorId, Map<String, Object> params) {
        if (params == null || !params.containsKey("staffId")) {
            return false;
        }

        Object staffObj = params.get("staffId");
        if (!(staffObj instanceof Integer)) {
            // some callers may pass numbers as Strings; be lenient
            try {
                int staffId = Integer.parseInt(String.valueOf(staffObj));
                return complaintDAO.assignComplaintToStaff(complaintId, staffId);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        int staffId = (Integer) staffObj;
        return complaintDAO.assignComplaintToStaff(complaintId, staffId);
    }
}
