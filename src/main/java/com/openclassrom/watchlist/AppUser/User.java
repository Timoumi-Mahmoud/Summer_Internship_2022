package com.openclassrom.watchlist.AppUser;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class User   {
/*
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String username;
private String lastName;
private String email;
private String password;
private int age;

private int active;

private String roles="";

private String permession="";


    public User(String username, String password, String roles, String permession) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permession = permession;
        this.active=1;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermession() {
        return permession;
    }

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permession.length()>0){
            return Arrays.asList(this.permession.split(","));
        }
        return new ArrayList<>();
    }
*/

}
