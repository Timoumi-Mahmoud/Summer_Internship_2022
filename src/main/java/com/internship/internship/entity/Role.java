package com.internship.internship.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role  extends Auditable<String> {
    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "this field can't be empty")
    private String name;
    @Size(min = 10, max = 200, message
            = "Description must be between 10 and 200 characters")
    private String descriptionOfTheRole;





    public Role(Integer id, String name, String descriptionOfTheRole) {
        this.id = id;
        this.name = name;
        this.descriptionOfTheRole = descriptionOfTheRole;
    }

    public Integer getId() {
            return id;
        }




    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Role() {

    }

    public String getDescriptionOfTheRole() {
        return descriptionOfTheRole;
    }

    public void setDescriptionOfTheRole(String descriptionOfTheRole) {
        this.descriptionOfTheRole = descriptionOfTheRole;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
