package com.internship.internship.services;


import com.internship.internship.entity.Role;
import com.internship.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public List<Role> findAll(){return roleRepository.findAll();}
    public void delete(int id){
        roleRepository.deleteById(id);
    }
    public Role findBy(int id){
        return roleRepository.findById(id).get();
    }
    public Role  save(Role role){return roleRepository.saveAndFlush(role);}
    public Role update(Role role,int id){
        Role oldRole= roleRepository.findById(id).get();
        System.out.println(" the old one::::"+ oldRole);
        oldRole.setId(role.getId());
        oldRole.setName(role.getName());
      //  oldRole.setNameRole(role.getNameRole());

        roleRepository.save(role);
        return role;
    }





}
