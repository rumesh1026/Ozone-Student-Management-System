package com.ozoneinstitute.controller;

import com.ozoneinstitute.dao.CourseDAO;
import com.ozoneinstitute.dao.ScheduleDAO;
import com.ozoneinstitute.model.Schedule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ScheduleController", urlPatterns = {"/schedules/*"})
public class ScheduleController extends HttpServlet {
    private ScheduleDAO scheduleDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        scheduleDAO = new ScheduleDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listSchedules(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteSchedule(request, response);
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
                addSchedule(request, response);
            } else if (action.equals("/update")) {
                updateSchedule(request, response);
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listSchedules(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Schedule> schedules = scheduleDAO.getAllSchedules();
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("/jsp/viewSchedules.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("courses", courseDAO.getAllCourses());
        request.getRequestDispatcher("/jsp/addSchedule.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("id"));
        Schedule schedule = scheduleDAO.getScheduleById(scheduleId);
        request.setAttribute("schedule", schedule);
        request.setAttribute("courses", courseDAO.getAllCourses());
        request.getRequestDispatcher("/jsp/addSchedule.jsp").forward(request, response);
    }

    private void addSchedule(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String location = request.getParameter("location");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(timeFormat.parse(startTimeStr).getTime());
        Time endTime = new Time(timeFormat.parse(endTimeStr).getTime());

        Schedule schedule = new Schedule(0, courseId, dayOfWeek, startTime, endTime, location);
        scheduleDAO.addSchedule(schedule);
        response.sendRedirect(request.getContextPath() + "/schedules");
    }

    private void updateSchedule(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String location = request.getParameter("location");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time startTime = new Time(timeFormat.parse(startTimeStr).getTime());
        Time endTime = new Time(timeFormat.parse(endTimeStr).getTime());

        Schedule schedule = new Schedule(scheduleId, courseId, dayOfWeek, startTime, endTime, location);
        scheduleDAO.updateSchedule(schedule);
        response.sendRedirect(request.getContextPath() + "/schedules");
    }

    private void deleteSchedule(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("id"));
        scheduleDAO.deleteSchedule(scheduleId);
        response.sendRedirect(request.getContextPath() + "/schedules");
    }
}
