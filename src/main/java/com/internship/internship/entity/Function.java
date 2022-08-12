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



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "Functions_roles",
            joinColumns = @JoinColumn(name = "id_function"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> RolesF = new HashSet<>();

    @ManyToOne
    private Function parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Function> children = new HashSet<>();


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

    public Set<Role> getRolesF() {
        return RolesF;
    }

    public void setRolesF(Set<Role> rolesF) {
        RolesF = rolesF;
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

    @Override
    public String toString() {
        return
               nameFunction ;

    }
}