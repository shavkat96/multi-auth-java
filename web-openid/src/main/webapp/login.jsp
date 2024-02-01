<%-- 
    Document   : login
    Created on : Dec 18, 2023, 7:07:29 AM
    Author     : Maiwand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://cdn.tailwindcss.com"></script>
        <title>Login (Google)</title>
    </head>
    <body>
        <div class="flex h-screen items-center bg-indigo-100">
            <div class="y-1/2 mx-auto w-1/2 rounded-xl border bg-white p-5 shadow-2xl md:w-1/3 md:p-3">
                <div class="mt-5 flex items-center justify-center gap-2">
                    <div class="h-8 w-8 items-center rounded-full bg-indigo-700 text-center text-lg font-semibold text-white">G</div>
                    <div class="text-center text-3xl font-semibold text-indigo-900 underline">Log In</div>
                </div>
                <form class="mt-10 flex flex-col gap-2 px-10" action="login" method="post">
                    <div class="flex">
                        <input class="mx-auto rounded-lg bg-indigo-700 px-3 py-2 text-white hover:bg-indigo-400" type="submit" value="Login with Google" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>