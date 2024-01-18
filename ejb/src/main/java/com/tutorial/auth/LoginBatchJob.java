package com.tutorial.auth;

/**
 *
 * @author Maiwand
 */
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@SecurityDomain("BusinessDomain")
public class LoginBatchJob {

    private static final Logger LOGGER = Logger.getLogger(LoginBatchJob.class.getName());

    @Resource
    private SessionContext sessionContext;

    @EJB
    private LoginDAO loginDAO;

    @PermitAll
    public void loginUser(String username) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(username);
        loginEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

        LOGGER.log(Level.INFO, "Logging in user: {0} at timestamp: {1}", new Object[]{username, loginEntity.getTimestamp()});

        loginDAO.save(loginEntity);
    }

    @PermitAll
    public boolean isLoggedIn() {
        final String name = sessionContext != null && sessionContext.getCallerPrincipal() != null
                ? sessionContext.getCallerPrincipal().getName()
                : null;
        return name != null && !name.equalsIgnoreCase("guest") && !name.equalsIgnoreCase("anonymous");
    }

    @PermitAll
    public String getUsername() {
        if (sessionContext != null && sessionContext.getCallerPrincipal() != null && !sessionContext.getCallerPrincipal().getName().equalsIgnoreCase("guest") && !sessionContext.getCallerPrincipal().getName().equalsIgnoreCase("anonymous")) {
            return sessionContext.getCallerPrincipal().getName();
        } else {
            return "";
        }
    }
}
