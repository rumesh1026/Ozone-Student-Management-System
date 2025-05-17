<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${schedule != null}">Edit Schedule</c:when><c:otherwise>Add Schedule</c:otherwise></c:choose> - Ozone Institute</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen font-sans">
<header class="bg-gradient-to-r from-cyan-600 to-blue-700 text-white py-6 shadow">
    <h1 class="text-3xl font-bold text-center tracking-wide">Ozone Institute - Schedules</h1>
</header>
<nav class="flex justify-center gap-6 py-4 bg-white shadow">
    <a href="<%=request.getContextPath()%>/students" class="text-cyan-700 font-semibold hover:underline">Students</a>
    <a href="<%=request.getContextPath()%>/instructors" class="text-cyan-700 font-semibold hover:underline">Instructors</a>
    <a href="<%=request.getContextPath()%>/departments" class="text-cyan-700 font-semibold hover:underline">Departments</a>
    <a href="<%=request.getContextPath()%>/courses" class="text-cyan-700 font-semibold hover:underline">Courses</a>
    <a href="<%=request.getContextPath()%>/registrations" class="text-cyan-700 font-semibold hover:underline">Registrations</a>
    <a href="<%=request.getContextPath()%>/schedules" class="text-cyan-700 font-semibold hover:underline">Schedules</a>
</nav>
<main class="max-w-lg mx-auto py-10 px-4">
    <div class="bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${schedule != null}">Edit Schedule</c:when>
                <c:otherwise>Add New Schedule</c:otherwise>
            </c:choose>
        </h2>
        <form action="<%=request.getContextPath()%>/schedules/${schedule != null ? 'update' : 'add'}" method="post" class="space-y-5">
            <c:if test="${schedule != null}">
                <input type="hidden" name="id" value="${schedule.scheduleId}">
            </c:if>
            <div>
                <label for="courseId" class="block text-gray-700 font-medium mb-1">Course</label>
                <select id="courseId" name="courseId" required
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-cyan-500">
                    <option value="">-- Select Course --</option>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.courseId}"
                                <c:if test="${schedule != null && schedule.courseId == course.courseId}">selected</c:if>
                        >${course.courseName} (${course.courseCode})</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="dayOfWeek" class="block text-gray-700 font-medium mb-1">Day of Week</label>
                <select id="dayOfWeek" name="dayOfWeek" required
                        class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-cyan-500">
                    <option value="">-- Select Day --</option>
                    <c:forEach var="day" items="${['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']}">
                        <option value="${day}"
                                <c:if test="${schedule != null && schedule.dayOfWeek == day}">selected</c:if>
                        >${day}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="startTime" class="block text-gray-700 font-medium mb-1">Start Time</label>
                <input type="time" id="startTime" name="startTime" required
                       value="<c:out value='${schedule != null ? schedule.startTime : ""}'/>"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-cyan-500">
            </div>
            <div>
                <label for="endTime" class="block text-gray-700 font-medium mb-1">End Time</label>
                <input type="time" id="endTime" name="endTime" required
                       value="<c:out value='${schedule != null ? schedule.endTime : ""}'/>"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-cyan-500">
            </div>
            <div>
                <label for="location" class="block text-gray-700 font-medium mb-1">Location</label>
                <input type="text" id="location" name="location" required
                       value="${schedule != null ? schedule.location : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-cyan-500">
            </div>
            <button type="submit" class="w-full bg-cyan-600 hover:bg-cyan-700 text-white py-2 rounded font-semibold transition">
                <c:choose>
                    <c:when test="${schedule != null}">Update Schedule</c:when>
                    <c:otherwise>Add Schedule</c:otherwise>
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
