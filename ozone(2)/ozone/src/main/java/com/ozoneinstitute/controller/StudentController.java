package com.ozoneinstitute.controller;

import com.ozoneinstitute.dao.StudentDAO;
import com.ozoneinstitute.model.Student;
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

@WebServlet(name = "StudentController", urlPatterns = {"/students/*"})
public class StudentController extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listStudents(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteStudent(request, response);
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
                addStudent(request, response);
            } else if (action.equals("/update")) {
                updateStudent(request, response);
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/jsp/viewStudents.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addStudent.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(studentId);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/jsp/addStudent.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String joinDateStr = request.getParameter("joinDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date joinDate = sdf.parse(joinDateStr);

        Student student = new Student(0, firstName, lastName, email, phoneNumber, joinDate);
        studentDAO.addStudent(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String joinDateStr = request.getParameter("joinDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date joinDate = sdf.parse(joinDateStr);

        Student student = new Student(studentId, firstName, lastName, email, phoneNumber, joinDate);
        studentDAO.updateStudent(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(studentId);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}
