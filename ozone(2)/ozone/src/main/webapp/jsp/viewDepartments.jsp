<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Departments - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-purple-600 to-blue-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Departments</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-purple-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-purple-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-purple-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-purple-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-purple-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-purple-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-4xl mx-auto py-8 px-4">
    <div class="flex justify-between items-center mb-6">
        <h2 class="text-2xl font-semibold text-gray-800">Department List</h2>
        <a href="<%=request.getContextPath()%>/departments/add" class="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded shadow font-semibold transition">+ Add Department</a>
    </div>
    <div class="overflow-x-auto bg-white rounded-lg shadow">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Code</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Head Instructor ID</th>
                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-100">
            <c:forEach var="department" items="${departments}">
                <tr class="hover:bg-purple-50 transition">
                    <td class="px-4 py-3">${department.departmentId}</td>
                    <td class="px-4 py-3">${department.departmentName}</td>
                    <td class="px-4 py-3">${department.departmentCode}</td>
                    <td class="px-4 py-3">
                        <c:out value="${department.headId != 0 ? department.headId : '-'}"/>
                    </td>
                    <td class="px-4 py-3 flex gap-3">
                        <a href="<%=request.getContextPath()%>/departments/edit?id=${department.departmentId}" class="text-blue-600 hover:text-blue-900 font-medium">Edit</a>
                        <a href="<%=request.getContextPath()%>/departments/delete?id=${department.departmentId}" class="text-red-600 hover:text-red-800 font-medium"
                           onclick="return confirm('Are you sure you want to delete this department?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty departments}">
                <tr>
                    <td colspan="5" class="text-center text-gray-500 py-6">No departments found.</td>
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
