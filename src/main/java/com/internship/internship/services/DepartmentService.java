package com.internship.internship.services;


import com.internship.internship.entity.Department;
import com.internship.internship.entity.Role;
import com.internship.internship.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll(){


        return departmentRepository.findAll();}

    public Page<Department> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return departmentRepository.findAll(pageable);
    }

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
