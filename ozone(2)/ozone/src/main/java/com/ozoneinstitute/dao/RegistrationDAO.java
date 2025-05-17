package com.ozoneinstitute.dao;

import com.ozoneinstitute.model.Registration;
import com.ozoneinstitute.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    public void addRegistration(Registration registration) throws SQLException {
        String sql = "INSERT INTO registrations (student_id, course_id, registration_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, registration.getStudentId());
            stmt.setInt(2, registration.getCourseId());
            stmt.setDate(3, new java.sql.Date(registration.getRegistrationDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Registration getRegistrationById(int registrationId) throws SQLException {
        String sql = "SELECT * FROM registrations WHERE registration_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, registrationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Registration(
                        rs.getInt("registration_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("registration_date")
                );
            }
        }
        return null;
    }

    public List<Registration> getAllRegistrations() throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT * FROM registrations";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                registrations.add(new Registration(
                        rs.getInt("registration_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("registration_date")
                ));
            }
        }
        return registrations;
    }

    public List<Registration> getRegistrationsByStudentId(int studentId) throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT * FROM registrations WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                registrations.add(new Registration(
                        rs.getInt("registration_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("registration_date")
                ));
            }
        }
        return registrations;
    }

    public List<Registration> getRegistrationsByCourseId(int courseId) throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT * FROM registrations WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                registrations.add(new Registration(
                        rs.getInt("registration_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("registration_date")
                ));
            }
        }
        return registrations;
    }

    public void updateRegistration(Registration registration) throws SQLException {
        String sql = "UPDATE registrations SET student_id = ?, course_id = ?, registration_date = ? WHERE registration_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, registration.getStudentId());
            stmt.setInt(2, registration.getCourseId());
            stmt.setDate(3, new java.sql.Date(registration.getRegistrationDate().getTime()));
            stmt.setInt(4, registration.getRegistrationId());
            stmt.executeUpdate();
        }
    }

    public void deleteRegistration(int registrationId) throws SQLException {
        String sql = "DELETE FROM registrations WHERE registration_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, registrationId);
            stmt.executeUpdate();
        }
    }
}
