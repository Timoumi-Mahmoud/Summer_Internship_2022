package com.internship.internship.AppUser;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {




        @Id
        @Column(name = "id_role")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;
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
