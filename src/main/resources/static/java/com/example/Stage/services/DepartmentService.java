package com.example.Stage.services;

import com.example.Stage.entity.Department;
import com.example.Stage.entity.User;
import com.example.Stage.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll(){


        return departmentRepository.findAll();}
    public void delete(int idDepartment){
        departmentRepository.deleteById(idDepartment);
    }
    public Department findBy(int idDepartment){
        return departmentRepository.findById(idDepartment).get();
    }
    public Department  save(Department department){return departmentRepository.saveAndFlush(department);}
    public Department update(Department department,int idDeparment){
        Department oldDept= departmentRepository.findById(idDeparment).get();
        System.out.println(" the old one::::"+ oldDept);
        oldDept.setIdDepartment(department.getIdDepartment());
        oldDept.setDepartmentName(department.getDepartmentName());
        oldDept.setResponsibleEmail(department.getResponsibleEmail());
        //oldDept.setUsers(department.getUsers());

        System.out.println("the new users is::::"+department.getUsers());
        System.out.println("the new to user"+ department);
        departmentRepository.save(department);
        return department;
    }





}
