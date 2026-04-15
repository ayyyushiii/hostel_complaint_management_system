-- Hostel Complaint Management System Database Schema
-- MySQL Database Creation Script

-- Create Database
CREATE DATABASE IF NOT EXISTS hostel_complaint_db;
USE hostel_complaint_db;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'STAFF', 'ADMIN') NOT NULL,
    room_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Complaints Table
CREATE TABLE IF NOT EXISTS complaints (
    complaint_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    category ENUM('ELECTRICAL', 'PLUMBING', 'INTERNET', 'CLEANLINESS', 'SECURITY', 'FURNITURE') NOT NULL,
    description TEXT NOT NULL,
    room_number VARCHAR(20) NOT NULL,
    status ENUM('SUBMITTED', 'ASSIGNED', 'IN_PROGRESS', 'RESOLVED', 'CLOSED') DEFAULT 'SUBMITTED',
    assigned_staff INT,
    notes TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_closed TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_staff) REFERENCES users(user_id) ON DELETE SET NULL
);

-- Create Complaint Timeline Table
CREATE TABLE IF NOT EXISTS complaint_timeline (
    timeline_id INT PRIMARY KEY AUTO_INCREMENT,
    complaint_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    performed_by INT NOT NULL,
    remarks TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (complaint_id) REFERENCES complaints(complaint_id) ON DELETE CASCADE,
    FOREIGN KEY (performed_by) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Create Indexes for Better Query Performance
CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_user_role ON users(role);
CREATE INDEX idx_complaint_student ON complaints(student_id);
CREATE INDEX idx_complaint_staff ON complaints(assigned_staff);
CREATE INDEX idx_complaint_status ON complaints(status);
CREATE INDEX idx_complaint_category ON complaints(category);
CREATE INDEX idx_timeline_complaint ON complaint_timeline(complaint_id);
CREATE INDEX idx_timeline_timestamp ON complaint_timeline(timestamp);

-- Insert Sample Data (Optional)
-- Admin User
INSERT INTO users (name, email, password, role, room_number)
VALUES ('Warden Admin', 'admin@hostel.com', 'admin@123', 'ADMIN', NULL);

-- Maintenance Staff Users
INSERT INTO users (name, email, password, role, room_number)
VALUES
('John Maintenance', 'john@hostel.com', 'john@123', 'STAFF', NULL),
('Sarah Repair', 'sarah@hostel.com', 'sarah@123', 'STAFF', NULL);

-- Student Users
INSERT INTO users (name, email, password, role, room_number)
VALUES
('Alice Student', 'alice@student.com', 'alice@123', 'STUDENT', 'A-101'),
('Bob Student', 'bob@student.com', 'bob@123', 'STUDENT', 'B-205');
