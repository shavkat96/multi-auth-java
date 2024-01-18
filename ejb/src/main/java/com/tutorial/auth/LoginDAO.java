package com.tutorial.auth;

/**
 *
 * @author Maiwand
 */
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class LoginDAO {

    @PersistenceContext(unitName = "loginPU")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(LoginEntity loginEntity) {
        try {
            entityManager.persist(loginEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
