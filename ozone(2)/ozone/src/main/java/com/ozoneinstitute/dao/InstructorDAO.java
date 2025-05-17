package com.ozoneinstitute.dao;

import com.ozoneinstitute.model.Instructor;
import com.ozoneinstitute.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    public void addInstructor(Instructor instructor) throws SQLException {
        String sql = "INSERT INTO instructors (first_name, last_name, email, phone_number, department_id, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, instructor.getFirstName());
            stmt.setString(2, instructor.getLastName());
            stmt.setString(3, instructor.getEmail());
            stmt.setString(4, instructor.getPhoneNumber());
            if (instructor.getDepartmentId() > 0) {
                stmt.setInt(5, instructor.getDepartmentId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setDate(6, new java.sql.Date(instructor.getHireDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Instructor getInstructorById(int instructorId) throws SQLException {
        String sql = "SELECT * FROM instructors WHERE instructor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Instructor(
                        rs.getInt("instructor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getInt("department_id"),
                        rs.getDate("hire_date")
                );
            }
        }
        return null;
    }

    public List<Instructor> getAllInstructors() throws SQLException {
        List<Instructor> instructors = new ArrayList<>();
        String sql = "SELECT * FROM instructors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                instructors.add(new Instructor(
                        rs.getInt("instructor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getInt("department_id"),
                        rs.getDate("hire_date")
                ));
            }
        }
        return instructors;
    }

    public void updateInstructor(Instructor instructor) throws SQLException {
        String sql = "UPDATE instructors SET first_name = ?, last_name = ?, email = ?, phone_number = ?, department_id = ?, hire_date = ? WHERE instructor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, instructor.getFirstName());
            stmt.setString(2, instructor.getLastName());
            stmt.setString(3, instructor.getEmail());
            stmt.setString(4, instructor.getPhoneNumber());
            if (instructor.getDepartmentId() > 0) {
                stmt.setInt(5, instructor.getDepartmentId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setDate(6, new java.sql.Date(instructor.getHireDate().getTime()));
            stmt.setInt(7, instructor.getInstructorId());
            stmt.executeUpdate();
        }
    }

    public void deleteInstructor(int instructorId) throws SQLException {
        String sql = "DELETE FROM instructors WHERE instructor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            stmt.executeUpdate();
        }
    }
}
