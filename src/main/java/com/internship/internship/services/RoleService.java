package com.internship.internship.services;


import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.Role;
import com.internship.internship.entity.User;
import com.internship.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }



    public Page<Role> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return roleRepository.findAll(pageable);
    }


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


    public void assignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }
    public ResponseEntity<Object> deleteRole(int id) {
        if(roleRepository.findById(id).isPresent()){
            if(roleRepository.getOne(id).getUserss().size() == 0) {
                roleRepository.deleteById(id);
                if (roleRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
                } else return ResponseEntity.ok().body("Successfully deleted specified record");
            } else return ResponseEntity.unprocessableEntity().body("Failed to delete,  Please delete the users associated with this role");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }


}
