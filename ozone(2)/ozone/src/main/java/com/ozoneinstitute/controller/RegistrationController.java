package com.ozoneinstitute.controller;

import com.ozoneinstitute.dao.CourseDAO;
import com.ozoneinstitute.dao.RegistrationDAO;
import com.ozoneinstitute.dao.StudentDAO;
import com.ozoneinstitute.model.Registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RegistrationController", urlPatterns = {"/registrations/*"})
public class RegistrationController extends HttpServlet {
    private RegistrationDAO registrationDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        registrationDAO = new RegistrationDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listRegistrations(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/delete")) {
                deleteRegistration(request, response);
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
                addRegistration(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listRegistrations(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Registration> registrations = registrationDAO.getAllRegistrations();
        request.setAttribute("registrations", registrations);
        request.getRequestDispatcher("/jsp/viewRegistrations.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("students", studentDAO.getAllStudents());
        request.setAttribute("courses", courseDAO.getAllCourses());
        request.getRequestDispatcher("/jsp/registerStudent.jsp").forward(request, response);
    }

    private void addRegistration(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Registration registration = new Registration(0, studentId, courseId, new Date());
        registrationDAO.addRegistration(registration);
        response.sendRedirect(request.getContextPath() + "/registrations");
    }

    private void deleteRegistration(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int registrationId = Integer.parseInt(request.getParameter("id"));
        registrationDAO.deleteRegistration(registrationId);
        response.sendRedirect(request.getContextPath() + "/registrations");
    }
}
