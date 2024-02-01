<%-- 
    Document   : home
    Created on : Dec 20, 2023, 11:08:24 PM
    Author     : Maiwand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://cdn.tailwindcss.com"></script>
    <title>2FA Authentication</title>
  </head>
  <body>
    <div class="flex h-screen items-center bg-indigo-100">
      <div class="y-1/2 mx-auto w-1/2 rounded-xl border bg-white p-2 shadow-2xl md:w-1/3 md:p-3">
        <h2 class="mt-5 text-center text-3xl font-semibold text-indigo-900 underline">Two-Step</h2>
        <form class="mt-10 flex flex-col gap-2 px-10" action="verify" method="post">
          <div class="flex items-center gap-2">
            <label class="text-md w-32 font-medium text-indigo-600" for="code">One-Time Code</label>
            <input class="w-full rounded-lg border border-indigo-200 p-2 outline-none" id="code" name="code" />
          </div>
          <div class="mt-10 flex">
            <input class="mx-auto w-20 rounded-lg bg-indigo-700 py-2 text-white hover:bg-indigo-400" type="submit" value="Verify" />
          </div>
        </form>
      </div>
    </div>
  </body>
</html>