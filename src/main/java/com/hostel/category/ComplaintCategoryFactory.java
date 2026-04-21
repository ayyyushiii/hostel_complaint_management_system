package com.hostel.category;

import com.hostel.model.Complaint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Factory responsible for discovering and creating complaint categories.
 */
public class ComplaintCategoryFactory {
    private final Map<String, ComplaintCategory> categories;

    public ComplaintCategoryFactory() {
        this.categories = loadCategories();
    }

    public Complaint createComplaint(String categoryCode, int studentId, String description, String roomNumber) {
        return getCategory(categoryCode).createComplaint(studentId, description, roomNumber);
    }

    public boolean supports(String categoryCode) {
        if (categoryCode == null) {
            return false;
        }

        return categories.containsKey(categoryCode.trim().toUpperCase());
    }

    public ComplaintCategory getCategory(String categoryCode) {
        if (categoryCode == null) {
            throw new IllegalArgumentException("Category is required");
        }

        ComplaintCategory category = categories.get(categoryCode.trim().toUpperCase());
        if (category == null) {
            throw new IllegalArgumentException("Unsupported complaint category: " + categoryCode);
        }

        return category;
    }

    public List<ComplaintCategory> getAvailableCategories() {
        Collection<ComplaintCategory> values = categories.values();
        return Collections.unmodifiableList(new ArrayList<>(values));
    }

    private Map<String, ComplaintCategory> loadCategories() {
        Map<String, ComplaintCategory> discoveredCategories = new LinkedHashMap<>();
        ServiceLoader<ComplaintCategory> loader = ServiceLoader.load(ComplaintCategory.class);

        for (ComplaintCategory category : loader) {
            discoveredCategories.put(category.getCode().trim().toUpperCase(), category);
        }

        if (discoveredCategories.isEmpty()) {
            throw new IllegalStateException("No complaint categories were discovered");
        }

        return discoveredCategories;
    }
}
