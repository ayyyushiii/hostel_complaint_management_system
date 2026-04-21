package com.hostel.service.impl;

import com.hostel.dao.ComplaintDAO;
import com.hostel.service.ComplaintModifier;
import java.util.Map;

/**
 * Modifier to update complaint status and optionally notes.
 * Expected params:
 * - "status": String (required)
 * - "notes": String (optional)
 */
public class UpdateStatusModifier implements ComplaintModifier {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    public boolean apply(int complaintId, int actorId, Map<String, Object> params) {
        if (params == null || !params.containsKey("status")) {
            return false;
        }

        Object statusObj = params.get("status");
        if (!(statusObj instanceof String)) {
            return false;
        }

        String status = (String) statusObj;
        boolean ok = complaintDAO.updateComplaintStatus(complaintId, status);

        if (ok && params.containsKey("notes")) {
            Object notesObj = params.get("notes");
            if (notesObj instanceof String) {
                String notes = (String) notesObj;
                if (notes != null && !notes.trim().isEmpty()) {
                    complaintDAO.updateComplaintNotes(complaintId, notes);
                }
            }
        }

        return ok;
    }
}
