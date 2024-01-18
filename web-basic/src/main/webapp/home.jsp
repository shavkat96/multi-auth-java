<%-- 
    Document   : home
    Created on : Dec 14, 2023, 11:08:24 PM
    Author     : Maiwand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://cdn.tailwindcss.com"></script>
        <title>Home</title>
    </head>
    <body>
        <div class="h-screen bg-indigo-200">
            <h1 class="p-5 text-3xl font-semibold text-indigo-900">Welcome</h1>
            <p class="p-5 text-[40px] font-extrabold text-indigo-800">
                Hi! , ${username}</p>
        </div>
    </body>
</html>