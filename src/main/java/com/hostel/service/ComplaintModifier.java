package com.hostel.service;

import java.util.Map;

/**
 * ComplaintModifier defines an operation that modifies a complaint.
 * Implementations encapsulate specific modification behaviors so new
 * behaviors can be added without changing callers (OCP).
 */
public interface ComplaintModifier {
    /**
     * Apply modification to a complaint.
     *
     * @param complaintId complaint id to modify
     * @param actorId     id of the user performing the action (staff/admin)
     * @param params      additional parameters required by the modifier (keys
     *                    defined by implementations)
     * @return true if modification succeeded, false otherwise
     */
    boolean apply(int complaintId, int actorId, Map<String, Object> params);
}
