package com.internship.internship.entity;


import com.internship.internship.entity.Role;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id_user")
private int idUser;
private String email;


private String password;


    private  String firstName;
    private  String lastName;
    private  String sex;
    private Date birthDate;
    private  Date hireDate;
    private  int tel;
    private  String address;




    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = ""),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();










    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;







    public User(String email, String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }




    public User(String email, String password, String roles) {
        this.email = email;
        this.password = password;



    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    /*
    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
*/


}