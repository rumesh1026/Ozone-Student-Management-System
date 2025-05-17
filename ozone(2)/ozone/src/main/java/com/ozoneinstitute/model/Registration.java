package com.ozoneinstitute.model;

import java.util.Date;

public class Registration {
    private int registrationId;
    private int studentId;
    private int courseId;
    private Date registrationDate;

    // Constructors
    public Registration() {}

    public Registration(int registrationId, int studentId, int courseId, Date registrationDate) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public int getRegistrationId() { return registrationId; }
    public void setRegistrationId(int registrationId) { this.registrationId = registrationId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
