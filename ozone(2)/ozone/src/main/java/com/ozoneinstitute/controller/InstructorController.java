package com.ozoneinstitute.controller;

import com.ozoneinstitute.dao.CourseDAO;
import com.ozoneinstitute.dao.InstructorDAO;
import com.ozoneinstitute.dao.DepartmentDAO;
import com.ozoneinstitute.model.Instructor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "InstructorController", urlPatterns = {"/instructors/*"})
public class InstructorController extends HttpServlet {
    private InstructorDAO instructorDAO;
    private DepartmentDAO departmentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        instructorDAO = new InstructorDAO();
        departmentDAO = new DepartmentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listInstructors(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteInstructor(request, response);
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
                addInstructor(request, response);
            } else if (action.equals("/update")) {
                updateInstructor(request, response);
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listInstructors(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Instructor> instructors = instructorDAO.getAllInstructors();
        request.setAttribute("instructors", instructors);
        request.getRequestDispatcher("/jsp/viewInstructors.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("departments", departmentDAO.getAllDepartments());
        request.getRequestDispatcher("/jsp/addInstructor.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int instructorId = Integer.parseInt(request.getParameter("id"));
        Instructor instructor = instructorDAO.getInstructorById(instructorId);
        request.setAttribute("instructor", instructor);
        request.setAttribute("departments", departmentDAO.getAllDepartments());
        request.getRequestDispatcher("/jsp/addInstructor.jsp").forward(request, response);
    }

    private void addInstructor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String hireDateStr = request.getParameter("hireDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate = sdf.parse(hireDateStr);

        Instructor instructor = new Instructor(0, firstName, lastName, email, phoneNumber, departmentId, hireDate);
        instructorDAO.addInstructor(instructor);
        response.sendRedirect(request.getContextPath() + "/instructors");
    }

    private void updateInstructor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int instructorId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String hireDateStr = request.getParameter("hireDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate = sdf.parse(hireDateStr);

        Instructor instructor = new Instructor(instructorId, firstName, lastName, email, phoneNumber, departmentId, hireDate);
        instructorDAO.updateInstructor(instructor);
        response.sendRedirect(request.getContextPath() + "/instructors");
    }

    private void deleteInstructor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int instructorId = Integer.parseInt(request.getParameter("id"));

        // First remove instructor from courses
        courseDAO.nullifyInstructorInCourses(instructorId);

        // Then delete the instructor
        instructorDAO.deleteInstructor(instructorId);
        response.sendRedirect(request.getContextPath() + "/instructors");
    }
}
