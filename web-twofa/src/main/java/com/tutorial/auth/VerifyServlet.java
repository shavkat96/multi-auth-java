package com.tutorial.auth;

import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maiwand
 */
@WebServlet(name = "VerifyServlet", urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet {

    private AuthenticationService authenticationService;

    @EJB
    private LoginBatchJob loginBatchJob;

    @Override
    public void init() throws ServletException {
        super.init();
        authenticationService = new AuthenticationService();
        try {
            authenticationService.initializeMsalClient();
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerifyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("2fa.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = loginBatchJob.getUsername();
        String code = req.getParameter("code");

        boolean isValid = authenticationService.validateOneTimePasscode(username, code);

        System.out.println("valid:" + isValid);
        HttpSession session = req.getSession();
        session.setAttribute("username", username);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
