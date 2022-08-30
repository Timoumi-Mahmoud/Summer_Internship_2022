package com.internship.internship.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "department")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","users"})
public class Department extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private int idDepartment;
    @Column(name = "department_name")
    @NotBlank(message = "this field can't be empty")
    private String departmentName;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    private String responsibleEmail;

    @NotBlank(message = "mobile Number is required")
    @Size(min = 8, max = 12)
    private String  responsiblePhone;


/*
    @OneToMany (mappedBy="dept") //sans (mappedBy="d") une table intermédiare
    //departement_employee sera créée
    private List<User> users;*/


      @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
      @OnDelete(action = OnDeleteAction.CASCADE)
      private Set<User> Users;

    public Department() {
    }

    public Department(int idDepartment, String departmentName, String responsibleEmail, String responsiblePhone) {
        this.idDepartment = idDepartment;
        this.departmentName = departmentName;
        this.responsibleEmail = responsibleEmail;
        this.responsiblePhone = responsiblePhone;
    }

    public Department(String departmentName, String responsibleEmail, String responsiblePhone) {
        this.departmentName = departmentName;
        this.responsibleEmail = responsibleEmail;
        this.responsiblePhone = responsiblePhone;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", departmentName='" + departmentName + '\'' +
                ", responsibleEmail='" + responsibleEmail + '\'' +
                ", responsiblePhone=" + responsiblePhone +

                '}';
    }
}

