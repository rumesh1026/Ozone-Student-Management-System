<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registrations - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-orange-600 to-pink-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Registrations</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-orange-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-orange-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-orange-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-orange-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-orange-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-orange-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-5xl mx-auto py-8 px-4">
    <div class="flex justify-between items-center mb-6">
        <h2 class="text-2xl font-semibold text-gray-800">Registration List</h2>
        <a href="<%=request.getContextPath()%>/registrations/add" class="bg-orange-600 hover:bg-orange-700 text-white px-4 py-2 rounded shadow font-semibold transition">+ Register Student</a>
    </div>
    <div class="overflow-x-auto bg-white rounded-lg shadow">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Student ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Course ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Registration Date</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-100">
            <c:forEach var="registration" items="${registrations}">
                <tr class="hover:bg-orange-50 transition">
                    <td class="px-4 py-3">${registration.registrationId}</td>
                    <td class="px-4 py-3">${registration.studentId}</td>
                    <td class="px-4 py-3">${registration.courseId}</td>
                    <td class="px-4 py-3">${registration.registrationDate}</td>
                    <td class="px-4 py-3">
                        <a href="<%=request.getContextPath()%>/registrations/delete?id=${registration.registrationId}" class="text-red-600 hover:text-red-800 font-medium"
                           onclick="return confirm('Are you sure you want to delete this registration?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty registrations}">
                <tr>
                    <td colspan="5" class="text-center text-gray-500 py-6">No registrations found.</td>
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
