<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${instructor != null}">Edit Instructor</c:when><c:otherwise>Add Instructor</c:otherwise></c:choose> - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-pink-600 to-indigo-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Instructors</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-pink-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-pink-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-pink-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-pink-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-pink-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-pink-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-lg mx-auto py-10 px-4">
    <div class="bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${instructor != null}">Edit Instructor</c:when>
                <c:otherwise>Add New Instructor</c:otherwise>
            </c:choose>
        </h2>
        <form action="<%=request.getContextPath()%>/instructors/${instructor != null ? 'update' : 'add'}" method="post" class="space-y-5">
            <c:if test="${instructor != null}">
                <input type="hidden" name="id" value="${instructor.instructorId}">
            </c:if>
            <div>
                <label for="firstName" class="block text-gray-700 font-medium mb-1">First Name</label>
                <input type="text" id="firstName" name="firstName" required
                       value="${instructor != null ? instructor.firstName : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
            </div>
            <div>
                <label for="lastName" class="block text-gray-700 font-medium mb-1">Last Name</label>
                <input type="text" id="lastName" name="lastName" required
                       value="${instructor != null ? instructor.lastName : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
            </div>
            <div>
                <label for="email" class="block text-gray-700 font-medium mb-1">Email</label>
                <input type="email" id="email" name="email"
                       value="${instructor != null ? instructor.email : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
            </div>
            <div>
                <label for="phoneNumber" class="block text-gray-700 font-medium mb-1">Phone Number</label>
                <input type="text" id="phoneNumber" name="phoneNumber"
                       value="${instructor != null ? instructor.phoneNumber : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
            </div>
            <div>
                <label for="departmentId" class="block text-gray-700 font-medium mb-1">Department</label>
                <select id="departmentId" name="departmentId"
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
                    <option value="0">-- Select Department --</option>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.departmentId}"
                                <c:if test="${instructor != null && instructor.departmentId == dept.departmentId}">selected</c:if>
                        >${dept.departmentName} (${dept.departmentCode})</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="hireDate" class="block text-gray-700 font-medium mb-1">Hire Date</label>
                <input type="date" id="hireDate" name="hireDate" required
                       value="<c:out value='${instructor != null ? instructor.hireDate : ""}'/>"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-pink-500">
            </div>
            <button type="submit" class="w-full bg-pink-600 hover:bg-pink-700 text-white py-2 rounded font-semibold transition">
                <c:choose>
                    <c:when test="${instructor != null}">Update Instructor</c:when>
                    <c:otherwise>Add Instructor</c:otherwise>
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
