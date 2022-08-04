package com.example.Stage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "role_user")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role_User {
    @Id
    @Column(name = "id_role_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoleUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,  cascade =  CascadeType.MERGE)
    @JoinColumn(name="id_user", referencedColumnName="id_user",insertable = true, updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User role_user;



    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade =  CascadeType.MERGE)
    @JoinColumn(name="id_role", referencedColumnName="id_role",insertable = true, updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date assignmentDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAssignmentDate;

    public Role_User() {
    }

    public Role_User(int idRoleUser, User role_user, Role role, Date assignmentDate, Date endAssignmentDate) {
        this.idRoleUser = idRoleUser;
        this.role_user = role_user;
        this.role = role;
        this.assignmentDate = assignmentDate;
        this.endAssignmentDate = endAssignmentDate;
    }

    public int getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(int idRoleUser) {
        this.idRoleUser = idRoleUser;
    }

    public User getRole_user() {
        return role_user;
    }

    public void setRole_user(User role_user) {
        this.role_user = role_user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getEndAssignmentDate() {
        return endAssignmentDate;
    }

    public void setEndAssignmentDate(Date endAssignmentDate) {
        this.endAssignmentDate = endAssignmentDate;
    }

    @Override
    public String toString() {
        return "Role_User{" +
                "idRoleUser=" + idRoleUser +
                ", role_user=" + role_user +
                ", role=" + role +
                ", assignmentDate=" + assignmentDate +
                ", endAssignmentDate=" + endAssignmentDate +
                '}';
    }
}
