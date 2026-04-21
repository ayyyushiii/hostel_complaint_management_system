package com.hostel.controller;

import com.hostel.category.ComplaintCategoryFactory;
import com.hostel.dao.ComplaintDAO;
import com.hostel.dao.TimelineDAO;
import com.hostel.model.Complaint;
import com.hostel.model.ComplaintTimeline;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ComplaintServlet
 * Handles complaint submission
 */
@WebServlet("/student/submit-complaint")
public class ComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintDAO complaintDAO = new ComplaintDAO();
    private TimelineDAO timelineDAO = new TimelineDAO();
    private ComplaintCategoryFactory complaintCategoryFactory = new ComplaintCategoryFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        setCategoryOptions(request);
        request.getRequestDispatcher("/views/student/submitComplaint.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int studentId = (Integer) session.getAttribute("userId");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String roomNumber = request.getParameter("roomNumber");

        // Validation
        if (category == null || category.trim().isEmpty() ||
            description == null || description.trim().isEmpty() ||
            roomNumber == null || roomNumber.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required");
            setCategoryOptions(request);
            request.getRequestDispatcher("/views/student/submitComplaint.jsp").forward(request, response);
            return;
        }

        if (!complaintCategoryFactory.supports(category)) {
            request.setAttribute("error", "Please select a valid complaint category");
            setCategoryOptions(request);
            request.getRequestDispatcher("/views/student/submitComplaint.jsp").forward(request, response);
            return;
        }

        Complaint complaint = complaintCategoryFactory.createComplaint(
            category,
            studentId,
            description,
            roomNumber
        );
        int complaintId = complaintDAO.submitComplaint(complaint);

        if (complaintId > 0) {
            // Add timeline entry
            ComplaintTimeline timeline = new ComplaintTimeline(
                complaintId,
                "Complaint Submitted",
                studentId,
                "Complaint submitted by student"
            );
            timelineDAO.addTimelineEntry(timeline);

            request.setAttribute("success", "Complaint submitted successfully");
            setCategoryOptions(request);
            request.getRequestDispatcher("/views/student/submitComplaint.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to submit complaint");
            setCategoryOptions(request);
            request.getRequestDispatcher("/views/student/submitComplaint.jsp").forward(request, response);
        }
    }

    private void setCategoryOptions(HttpServletRequest request) {
        request.setAttribute("categories", complaintCategoryFactory.getAvailableCategories());
    }
}
