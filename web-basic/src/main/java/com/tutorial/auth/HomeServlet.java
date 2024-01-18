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
@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"Admin"}))
public class HomeServlet extends HttpServlet {

    @EJB
    private LoginBatchJob loginBatchJob;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, IOException {
        String username = loginBatchJob.getUsername();

        SecurityIdentity identity = SecurityDomain.getCurrent().getCurrentSecurityIdentity();
        identity.runAs(() -> {
            loginBatchJob.loginUser(username);
        });

        req.setAttribute("username", username);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
