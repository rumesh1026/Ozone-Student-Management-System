<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${department != null}">Edit Department</c:when><c:otherwise>Add Department</c:otherwise></c:choose> - Ozone Institute</title>
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
<main class="max-w-lg mx-auto py-10 px-4">
    <div class="bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${department != null}">Edit Department</c:when>
                <c:otherwise>Add New Department</c:otherwise>
            </c:choose>
        </h2>
        <form action="<%=request.getContextPath()%>/departments/${department != null ? 'update' : 'add'}" method="post" class="space-y-5">
            <c:if test="${department != null}">
                <input type="hidden" name="id" value="${department.departmentId}">
            </c:if>
            <div>
                <label for="departmentName" class="block text-gray-700 font-medium mb-1">Department Name</label>
                <input type="text" id="departmentName" name="departmentName" required
                       value="${department != null ? department.departmentName : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-purple-500">
            </div>
            <div>
                <label for="departmentCode" class="block text-gray-700 font-medium mb-1">Department Code</label>
                <input type="text" id="departmentCode" name="departmentCode" required
                       value="${department != null ? department.departmentCode : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-purple-500">
            </div>
            <div>
                <label for="headId" class="block text-gray-700 font-medium mb-1">Head Instructor</label>
                <select id="headId" name="headId"
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-purple-500">
                    <option value="0">-- Select Head Instructor --</option>
                    <c:forEach var="inst" items="${instructors}">
                        <option value="${inst.instructorId}"
                                <c:if test="${department != null && department.headId == inst.instructorId}">selected</c:if>
                        >${inst.firstName} ${inst.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="w-full bg-purple-600 hover:bg-purple-700 text-white py-2 rounded font-semibold transition">
                <c:choose>
                    <c:when test="${department != null}">Update Department</c:when>
                    <c:otherwise>Add Department</c:otherwise>
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
