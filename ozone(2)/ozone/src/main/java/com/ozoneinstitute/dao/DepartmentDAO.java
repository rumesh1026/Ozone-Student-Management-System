package com.ozoneinstitute.dao;

import com.ozoneinstitute.model.Department;
import com.ozoneinstitute.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public void addDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO departments (department_name, department_code, head_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getDepartmentName());
            stmt.setString(2, department.getDepartmentCode());
            if (department.getHeadId() > 0) {
                stmt.setInt(3, department.getHeadId());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_code"),
                        rs.getInt("head_id")
                );
            }
        }
        return null;
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_code"),
                        rs.getInt("head_id")
                ));
            }
        }
        return departments;
    }

    public void updateDepartment(Department department) throws SQLException {
        String sql = "UPDATE departments SET department_name = ?, department_code = ?, head_id = ? WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getDepartmentName());
            stmt.setString(2, department.getDepartmentCode());
            if (department.getHeadId() > 0) {
                stmt.setInt(3, department.getHeadId());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setInt(4, department.getDepartmentId());
            stmt.executeUpdate();
        }
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        String sql = "DELETE FROM departments WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departmentId);
            stmt.executeUpdate();
        }
    }
}
