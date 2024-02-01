package com.tutorial.auth;

/**
 *
 * @author Maiwand
 */
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to enforce authentication on protected resources.
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed for this filter
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        if (httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/verify") || httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/2fa.jsp")) {
            chain.doFilter(req, resp);
            return;
        }

        String username = (String) httpRequest.getSession().getAttribute("username");
        if (username == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/verify");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        // No cleanup necessary for this filter
    }
}
