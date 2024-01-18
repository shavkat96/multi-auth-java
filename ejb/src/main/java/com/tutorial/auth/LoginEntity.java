package com.tutorial.auth;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Maiwand
 */
@Entity
@Table(name = "userlogs")
@NamedQueries({
    @NamedQuery(name = "LoginEntity.findAll", query = "SELECT l FROM LoginEntity l"),
    @NamedQuery(name = "LoginEntity.findById", query = "SELECT l FROM LoginEntity l WHERE l.id = :id"),
    @NamedQuery(name = "LoginEntity.findByUsername", query = "SELECT l FROM LoginEntity l WHERE l.username = :username"),
    @NamedQuery(name = "LoginEntity.findByTimestamp", query = "SELECT l FROM LoginEntity l WHERE l.timestamp = :timestamp")})
public class LoginEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public LoginEntity() {
    }

    public LoginEntity(Long id) {
        this.id = id;
    }
    
    public LoginEntity(String username, Timestamp timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
   
}
