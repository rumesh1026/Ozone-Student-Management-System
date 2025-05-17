package com.ozoneinstitute.dao;

import com.ozoneinstitute.model.Course;
import com.ozoneinstitute.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_name, course_code, department_id, instructor_id, credits) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getDepartmentId());
            if (course.getInstructorId() != null) {
                stmt.setInt(4, course.getInstructorId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, course.getCredits());
            stmt.executeUpdate();
        }
    }

    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getInt("department_id"),
                        rs.getObject("instructor_id") != null ? rs.getInt("instructor_id") : null,
                        rs.getInt("credits")
                );
            }
        }
        return null;
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getInt("department_id"),
                        rs.getObject("instructor_id") != null ? rs.getInt("instructor_id") : null,
                        rs.getInt("credits")
                ));
            }
        }
        return courses;
    }

    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET course_name = ?, course_code = ?, department_id = ?, instructor_id = ?, credits = ? WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getDepartmentId());
            if (course.getInstructorId() != null) {
                stmt.setInt(4, course.getInstructorId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, course.getCredits());
            stmt.setInt(6, course.getCourseId());
            stmt.executeUpdate();
        }
    }

    public void deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
        }
    }

    public void nullifyInstructorInCourses(int instructorId) throws SQLException {
        String sql = "UPDATE courses SET instructor_id = NULL WHERE instructor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            stmt.executeUpdate();
        }
    }
}
