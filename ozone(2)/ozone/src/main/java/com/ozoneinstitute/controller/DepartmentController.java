package com.ozoneinstitute.controller;

import com.ozoneinstitute.dao.DepartmentDAO;
import com.ozoneinstitute.model.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DepartmentController", urlPatterns = {"/departments/*"})
public class DepartmentController extends HttpServlet {
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listDepartments(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteDepartment(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action.equals("/add")) {
                addDepartment(request, response);
            } else if (action.equals("/update")) {
                updateDepartment(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/jsp/viewDepartments.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addDepartment.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("id"));
        Department department = departmentDAO.getDepartmentById(departmentId);
        request.setAttribute("department", department);
        request.getRequestDispatcher("/jsp/addDepartment.jsp").forward(request, response);
    }

    private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String departmentName = request.getParameter("departmentName");
        String departmentCode = request.getParameter("departmentCode");
        int headId = Integer.parseInt(request.getParameter("headId"));

        Department department = new Department(0, departmentName, departmentCode, headId);
        departmentDAO.addDepartment(department);
        response.sendRedirect(request.getContextPath() + "/departments");
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("id"));
        String departmentName = request.getParameter("departmentName");
        String departmentCode = request.getParameter("departmentCode");
        int headId = Integer.parseInt(request.getParameter("headId"));

        Department department = new Department(departmentId, departmentName, departmentCode, headId);
        departmentDAO.updateDepartment(department);
        response.sendRedirect(request.getContextPath() + "/departments");
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("id"));
        departmentDAO.deleteDepartment(departmentId);
        response.sendRedirect(request.getContextPath() + "/departments");
    }
}
