package com.tutorial.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import org.wildfly.security.auth.server.SecurityDomain;
import org.wildfly.security.auth.server.SecurityIdentity;

/**
 *
 * @author Maiwand
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"OAuthUser"}))
public class HomeServlet extends HttpServlet {
    
    @EJB
    LoginBatchJob loginBatchJob;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        System.out.println("Username:" + username);

        req.setAttribute("username", username);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
