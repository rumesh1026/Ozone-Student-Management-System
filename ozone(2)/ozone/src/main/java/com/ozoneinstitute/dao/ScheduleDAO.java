package com.ozoneinstitute.dao;

import com.ozoneinstitute.model.Schedule;
import com.ozoneinstitute.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public void addSchedule(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO schedules (course_id, day_of_week, start_time, end_time, location) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getCourseId());
            stmt.setString(2, schedule.getDayOfWeek());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.setString(5, schedule.getLocation());
            stmt.executeUpdate();
        }
    }

    public Schedule getScheduleById(int scheduleId) throws SQLException {
        String sql = "SELECT * FROM schedules WHERE schedule_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getInt("course_id"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getString("location")
                );
            }
        }
        return null;
    }

    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                schedules.add(new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getInt("course_id"),
                        rs.getString("day_of_week"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getString("location")
                ));
            }
        }
        return schedules;
    }

    public void deleteSchedulesByCourseId(int courseId) throws SQLException {
        String sql = "DELETE FROM schedules WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
        }
    }

    public void updateSchedule(Schedule schedule) throws SQLException {
        String sql = "UPDATE schedules SET course_id = ?, day_of_week = ?, start_time = ?, end_time = ?, location = ? WHERE schedule_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getCourseId());
            stmt.setString(2, schedule.getDayOfWeek());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.setString(5, schedule.getLocation());
            stmt.setInt(6, schedule.getScheduleId());
            stmt.executeUpdate();
        }
    }

    public void deleteSchedule(int scheduleId) throws SQLException {
        String sql = "DELETE FROM schedules WHERE schedule_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
        }
    }
}
