<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozone Institute - Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-blue-50 via-indigo-100 to-cyan-50 min-h-screen font-sans">
<!-- Header -->
<header class="bg-gradient-to-r from-blue-700 via-indigo-700 to-cyan-500 shadow-lg py-6 sm:py-8 lg:py-10">
    <div class="container mx-auto px-4 text-center">
        <h1 class="text-3xl sm:text-4xl lg:text-5xl font-extrabold text-white drop-shadow-lg tracking-wider mb-2">
            Ozone Institute
        </h1>
        <p class="text-base sm:text-lg lg:text-xl text-indigo-100 font-light">Empowering Education, Inspiring Futures</p>
    </div>
</header>

<!-- Main Content -->
<main class="container mx-auto px-4 sm:px-6 py-8 sm:py-12">
    <div class="max-w-3xl mx-auto text-center mb-8 sm:mb-12">
        <h2 class="text-xl sm:text-2xl lg:text-3xl font-bold text-gray-800 mb-4">Welcome to the Ozone Institute Management System</h2>
        <p class="text-sm sm:text-base text-gray-600 mb-2">
            Manage students, instructors, departments, courses, registrations, and schedules with ease.<br>
            Use the navigation cards below to get started.
        </p>
    </div>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 sm:gap-8 max-w-5xl mx-auto">
        <!-- Students -->
        <a href="<%=request.getContextPath()%>/students"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-blue-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-blue-600 group-hover:text-blue-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <circle cx="12" cy="10" r="4"/>
                    <path d="M6 18v-1c0-2.21 3.58-4 6-4s6 1.79 6 4v1"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Students</h3>
            <p class="text-gray-500 text-sm sm:text-base">View and manage student records.</p>
        </a>
        <!-- Instructors -->
        <a href="<%=request.getContextPath()%>/instructors"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-pink-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-pink-600 group-hover:text-pink-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <circle cx="12" cy="10" r="4"/>
                    <path d="M6 20v-2c0-2.21 3.58-4 6-4s6 1.79 6 4v2"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Instructors</h3>
            <p class="text-gray-500 text-sm sm:text-base">Manage instructor profiles and assignments.</p>
        </a>
        <!-- Departments -->
        <a href="<%=request.getContextPath()%>/departments"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-purple-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-purple-600 group-hover:text-purple-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <rect x="3" y="7" width="18" height="10" rx="2"/>
                    <path d="M3 7V5a2 2 0 012-2h14a2 2 0 012 2v2"/>
                    <path d="M8 21h8"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Departments</h3>
            <p class="text-gray-500 text-sm sm:text-base">Organize and manage academic departments.</p>
        </a>
        <!-- Courses -->
        <a href="<%=request.getContextPath()%>/courses"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-green-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-green-600 group-hover:text-green-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <rect x="4" y="4" width="16" height="16" rx="2"/>
                    <path d="M8 2v4M16 2v4M4 10h16"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Courses</h3>
            <p class="text-gray-500 text-sm sm:text-base">Manage course offerings and details.</p>
        </a>
        <!-- Registrations -->
        <a href="<%=request.getContextPath()%>/registrations"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-orange-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-orange-600 group-hover:text-orange-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <polygon points="12 2 22 8.5 12 15 2 8.5 12 2"/>
                    <path d="M12 15v7"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Registrations</h3>
            <p class="text-gray-500 text-sm sm:text-base">Register students for courses.</p>
        </a>
        <!-- Schedules -->
        <a href="<%=request.getContextPath()%>/schedules"
           class="group block bg-white rounded-xl shadow-md hover:shadow-xl transition p-6 sm:p-8 border border-gray-100 hover:border-cyan-400">
            <div class="flex items-center justify-center mb-4">
                <svg class="w-8 h-8 sm:w-10 sm:h-10 text-cyan-600 group-hover:text-cyan-800 transition" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <rect x="3" y="4" width="18" height="18" rx="2"/>
                    <path d="M16 2v4M8 2v4M3 10h18"/>
                </svg>
            </div>
            <h3 class="text-base sm:text-lg font-semibold text-gray-800 mb-1">Schedules</h3>
            <p class="text-gray-500 text-sm sm:text-base">View and manage class schedules.</p>
        </a>
    </div>
</main>
<footer class="bg-gray-50 text-gray-400 text-center py-4 sm:py-6 mt-12 sm:mt-16 text-xs sm:text-sm">
    © 2025 Ozone Institute. All rights reserved.
</footer>
</body>
</html>