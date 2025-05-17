<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses - Ozone Institute</title>
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
<main class="max-w-5xl mx-auto py-8 px-4">
    <div class="flex justify-between items-center mb-6">
        <h2 class="text-2xl font-semibold text-gray-800">Course List</h2>
        <a href="<%=request.getContextPath()%>/courses/add" class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded shadow font-semibold transition">+ Add Course</a>
    </div>
    <div class="overflow-x-auto bg-white rounded-lg shadow">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Code</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Department ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Instructor ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Credits</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-100">
            <c:forEach var="course" items="${courses}">
                <tr class="hover:bg-green-50 transition">
                    <td class="px-4 py-3">${course.courseId}</td>
                    <td class="px-4 py-3">${course.courseName}</td>
                    <td class="px-4 py-3">${course.courseCode}</td>
                    <td class="px-4 py-3">${course.departmentId}</td>
                    <td class="px-4 py-3">
                        <c:out value="${course.instructorId != null ? course.instructorId : '-'}"/>
                    </td>
                    <td class="px-4 py-3">${course.credits}</td>
                    <td class="px-4 py-3 flex gap-3">
                        <a href="<%=request.getContextPath()%>/courses/edit?id=${course.courseId}" class="text-blue-600 hover:text-blue-900 font-medium">Edit</a>
                        <a href="<%=request.getContextPath()%>/courses/delete?id=${course.courseId}" class="text-red-600 hover:text-red-800 font-medium"
                           onclick="return confirm('Are you sure you want to delete this course?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty courses}">
                <tr>
                    <td colspan="7" class="text-center text-gray-500 py-6">No courses found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>
<footer class="bg-gray-50 text-gray-400 text-center py-4 mt-10 text-sm">
    &copy; 2025 Ozone Institute. All rights reserved.
</footer>
</body>
</html>
