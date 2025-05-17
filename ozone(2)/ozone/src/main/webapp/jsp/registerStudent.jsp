<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Student - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-orange-600 to-pink-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Register Student</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-orange-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-orange-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-orange-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-orange-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-orange-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-orange-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-lg mx-auto py-10 px-4">
    <div class="bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">Register Student in Course</h2>
        <form action="<%=request.getContextPath()%>/registrations/add" method="post" class="space-y-5">
            <div>
                <label for="studentId" class="block text-gray-700 font-medium mb-1">Select Student</label>
                <select id="studentId" name="studentId" required
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-orange-500">
                    <option value="">-- Select Student --</option>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.studentId}">
                                ${student.firstName} ${student.lastName} (ID: ${student.studentId})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="courseId" class="block text-gray-700 font-medium mb-1">Select Course</label>
                <select id="courseId" name="courseId" required
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-orange-500">
                    <option value="">-- Select Course --</option>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.courseId}">
                                ${course.courseName} (${course.courseCode})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="w-full bg-orange-600 hover:bg-orange-700 text-white py-2 rounded font-semibold transition">
                Register Student
            </button>
        </form>
    </div>
</main>
<footer class="bg-gray-50 text-gray-400 text-center py-4 mt-10 text-sm">
    &copy; 2025 Ozone Institute. All rights reserved.
</footer>
</body>
</html>
