package com.hostel.category;

/**
 * Base implementation for simple complaint categories.
 */
public abstract class BaseComplaintCategory implements ComplaintCategory {
    private final String code;
    private final String displayName;

    protected BaseComplaintCategory(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
