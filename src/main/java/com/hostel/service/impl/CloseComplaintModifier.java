package com.hostel.service.impl;

import com.hostel.dao.ComplaintDAO;
import com.hostel.service.ComplaintModifier;
import java.util.Map;

/**
 * Modifier to close a complaint.
 * No params required.
 */
public class CloseComplaintModifier implements ComplaintModifier {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    public boolean apply(int complaintId, int actorId, Map<String, Object> params) {
        return complaintDAO.closeComplaint(complaintId);
    }
}
