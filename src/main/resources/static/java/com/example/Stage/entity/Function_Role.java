package com.example.Stage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "function_role")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Function_Role {
    @Id
    @Column(name = "id_function_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFunctionRole;

    @ManyToOne(fetch = FetchType.LAZY, optional = false , cascade =  CascadeType.MERGE)
    @JoinColumn(name="id_role", referencedColumnName="id_role",insertable = true, updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade =  CascadeType.MERGE)
    @JoinColumn(name="id_function", referencedColumnName="id_function",insertable = true, updatable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Function function;

    public Function_Role() {
    }

    public int getIdFunctionRole() {
        return idFunctionRole;
    }

    public void setIdFunctionRole(int idFunctionRole) {
        this.idFunctionRole = idFunctionRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Function_Role{" +
                "idFunctionRole=" + idFunctionRole +
                ", role=" + role +
                ", function=" + function +
                '}';
    }
}
