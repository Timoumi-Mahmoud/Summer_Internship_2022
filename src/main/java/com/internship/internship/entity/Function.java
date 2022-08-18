package com.internship.internship.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","parent"})


public class Function  extends Auditable<String> {

    @Id
    @Column(name = "id_function")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFunction;

    private String nameFunction;



    @ManyToMany(targetEntity = Role.class, mappedBy = "RolesFunction", cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Set<Role> RolesF ;

    @ManyToOne
    private Function parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Function> children ;


    public Function() {
    }

    public Function(String name, Function parent) {
        this.nameFunction = name;
        this.parent = parent;
    }

    public Function(int idFunction, String nameFunction, Function parent, Set<Function> children) {
        this.idFunction = idFunction;
        this.nameFunction = nameFunction;
        this.parent = parent;
        this.children = children;
    }

    public Function(int idFunction, String nameFunction) {
        this.idFunction = idFunction;
        this.nameFunction = nameFunction;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }

    public String getNameFunction() {
        return nameFunction;
    }

    public void setNameFunction(String nameFunction) {
        this.nameFunction = nameFunction;
    }




    public com.internship.internship.entity.Function getParent() {
        return parent;
    }

    public void setParent(com.internship.internship.entity.Function parent) {
        this.parent = parent;
    }

    public Set<com.internship.internship.entity.Function> getChildren() {
        return children;
    }

    public void setChildren(Set<com.internship.internship.entity.Function> children) {
        this.children = children;
    }

    @PreRemove
    private void removeFunctionFromRoles() {
        for (Role r : RolesF) {
            r.getRolesFunction().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Function{" +
                "nameFunction='" + nameFunction + '\'' +
                '}';
    }
}