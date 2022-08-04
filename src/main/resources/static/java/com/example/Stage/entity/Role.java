package com.example.Stage.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idRole;
    @NotBlank( message="Please enter the name of the role")
    private String nameRole;
    @NotBlank(message="Comment should be maximum 50 characters")
    private String DescriptionOfRole;


    public Role() {
    }

    public Role(int idRole, String nameRole, String descriptionOfRole) {
        this.idRole = idRole;
        this.nameRole = nameRole;
        DescriptionOfRole = descriptionOfRole;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescriptionOfRole() {
        return DescriptionOfRole;
    }

    public void setDescriptionOfRole(String descriptionOfRole) {
        DescriptionOfRole = descriptionOfRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", nameRole='" + nameRole + '\'' +
                ", DescriptionOfRole='" + DescriptionOfRole + '\'' +
                '}';
    }
}
