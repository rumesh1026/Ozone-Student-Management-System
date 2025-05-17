<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${course != null}">Edit Course</c:when><c:otherwise>Add Course</c:otherwise></c:choose> - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-green-600 to-blue-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Courses</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-green-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-green-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-green-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-green-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-green-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-green-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-lg mx-auto py-10 px-4">
    <div class="bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${course != null}">Edit Course</c:when>
                <c:otherwise>Add New Course</c:otherwise>
            </c:choose>
        </h2>
        <form action="<%=request.getContextPath()%>/courses/${course != null ? 'update' : 'add'}" method="post" class="space-y-5">
            <c:if test="${course != null}">
                <input type="hidden" name="id" value="${course.courseId}">
            </c:if>
            <div>
                <label for="courseName" class="block text-gray-700 font-medium mb-1">Course Name</label>
                <input type="text" id="courseName" name="courseName" required
                       value="${course != null ? course.courseName : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-500">
            </div>
            <div>
                <label for="courseCode" class="block text-gray-700 font-medium mb-1">Course Code</label>
                <input type="text" id="courseCode" name="courseCode" required
                       value="${course != null ? course.courseCode : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-500">
            </div>
            <div>
                <label for="departmentId" class="block text-gray-700 font-medium mb-1">Department</label>
                <select id="departmentId" name="departmentId" required
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-500">
                    <option value="">-- Select Department --</option>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.departmentId}"
                                <c:if test="${course != null && course.departmentId == dept.departmentId}">selected</c:if>
                        >${dept.departmentName} (${dept.departmentCode})</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="instructorId" class="block text-gray-700 font-medium mb-1">Instructor</label>
                <select id="instructorId" name="instructorId"
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-500">
                    <option value="">-- Select Instructor --</option>
                    <c:forEach var="inst" items="${instructors}">
                        <option value="${inst.instructorId}"
                                <c:if test="${course != null && course.instructorId == inst.instructorId}">selected</c:if>
                        >${inst.firstName} ${inst.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="credits" class="block text-gray-700 font-medium mb-1">Credits</label>
                <input type="number" id="credits" name="credits" required min="1" max="10"
                       value="${course != null ? course.credits : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-500">
            </div>
            <button type="submit" class="w-full bg-green-600 hover:bg-green-700 text-white py-2 rounded font-semibold transition">
                <c:choose>
                    <c:when test="${course != null}">Update Course</c:when>
                    <c:otherwise>Add Course</c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
</main>
<footer class="bg-gray-50 text-gray-400 text-center py-4 mt-10 text-sm">
    &copy; 2025 Ozone Institute. All rights reserved.
</footer>
</body>
</html>
