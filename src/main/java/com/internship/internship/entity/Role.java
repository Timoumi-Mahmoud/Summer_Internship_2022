package com.internship.internship.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {




        @Id
        @Column(name = "id_role")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;
        private String descriptionOfTheRole;



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