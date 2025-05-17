package com.ozoneinstitute.model;

public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;
    private int departmentId;
    private Integer instructorId; // Nullable
    private int credits;

    // Constructors
    public Course() {}

    public Course(int courseId, String courseName, String courseCode, int departmentId, Integer instructorId, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.departmentId = departmentId;
        this.instructorId = instructorId;
        this.credits = credits;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public Integer getInstructorId() { return instructorId; }
    public void setInstructorId(Integer instructorId) { this.instructorId = instructorId; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", departmentId=" + departmentId +
                ", instructorId=" + instructorId +
                ", credits=" + credits +
                '}';
    }
}
