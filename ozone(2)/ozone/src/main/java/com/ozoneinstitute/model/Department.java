package com.ozoneinstitute.model;

public class Department {
    private int departmentId;
    private String departmentName;
    private String departmentCode;
    private int headId; // References instructor_id

    // Constructors
    public Department() {}

    public Department(int departmentId, String departmentName, String departmentCode, int headId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.headId = headId;
    }

    // Getters and Setters
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public int getHeadId() { return headId; }
    public void setHeadId(int headId) { this.headId = headId; }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", headId=" + headId +
                '}';
    }
}
