package com.tutorial.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/", "/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private LoginBatchJob loginBatchJob;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null && !code.isEmpty()) {
            String token = GoogleAuthHelper.getToken(code);
            System.out.println(token);
            String username = GoogleAuthHelper.getUserInfo(token);
            if (username != null) {
                loginBatchJob.loginUser(username);
                HttpSession session = req.getSession();
                session.setAttribute("username", username);

                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginUrl = GoogleAuthHelper.buildLoginUrl();
        resp.sendRedirect(loginUrl);
    }
}
