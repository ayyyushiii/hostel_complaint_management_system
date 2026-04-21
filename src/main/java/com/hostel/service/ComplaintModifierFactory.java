package com.hostel.service;

import com.hostel.service.impl.AssignToStaffModifier;
import com.hostel.service.impl.CloseComplaintModifier;
import com.hostel.service.impl.UpdateStatusModifier;

/**
 * Simple factory to obtain ComplaintModifier implementations.
 * Callers use a string action to get the appropriate modifier.
 */
public class ComplaintModifierFactory {

    /**
     * Get modifier by action key. Recognized keys:
     * - "assign" -> AssignToStaffModifier
     * params: staffId
     * - "update_status" -> UpdateStatusModifier
     * params: status, notes
     * - "close" -> CloseComplaintModifier
     * params: none
     *
     * If unknown key is provided, returns null.
     */
    public static ComplaintModifier getModifier(String actionKey) {
        if (actionKey == null)
            return null;
        switch (actionKey) {
            case "assign":
                return new AssignToStaffModifier();
            case "update_status":
                return new UpdateStatusModifier();
            case "close":
                return new CloseComplaintModifier();
            default:
                return null;
        }
    }
}
