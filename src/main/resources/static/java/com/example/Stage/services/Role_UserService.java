package com.example.Stage.services;

import com.example.Stage.entity.Role_User;
import com.example.Stage.repository.Role_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Role_UserService {
    @Autowired
    Role_UserRepository role_userRepository;


    public List<Role_User> findAll() {
        return role_userRepository.findAll();
    }

    public void delete(int id) {
        role_userRepository.deleteById(id);
    }

    public Role_User findBy(int id) {
        return role_userRepository.findById(id).get();
    }

    public Role_User save(Role_User role_user ) {
        System.out.println(" the old one::::" + role_user);
        return role_userRepository.saveAndFlush(role_user);
    }

    public Role_User update(Role_User role_user, int id) {
        Role_User oldRole_user = role_userRepository.findById(id).get();
        System.out.println(" the old one::::" + oldRole_user);
        oldRole_user.setIdRoleUser(role_user.getIdRoleUser());
        oldRole_user.setAssignmentDate(role_user.getAssignmentDate());
        oldRole_user.setEndAssignmentDate(role_user.getEndAssignmentDate());
        oldRole_user.setRole_user(role_user.getRole_user());
        oldRole_user.setIdRoleUser(role_user.getIdRoleUser());

        role_userRepository.save(role_user);
        return role_user;
    }


}