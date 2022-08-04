package com.example.Stage.services;

import com.example.Stage.entity.Function_Role;
import com.example.Stage.entity.Role_User;
import com.example.Stage.repository.Function_RoleRepository;
import com.example.Stage.repository.Role_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Function_RoleService {
    @Autowired
    Function_RoleRepository function_roleRepository;


    public List<Function_Role> findAll() {
        return function_roleRepository.findAll();
    }

    public void delete(int id) {
        function_roleRepository.deleteById(id);
    }

    public Function_Role findBy(int id) {
        return function_roleRepository.findById(id).get();
    }

    public Function_Role save(Function_Role function_role ) {
        return function_roleRepository.saveAndFlush(function_role);
    }

    public Function_Role update(Function_Role function_role, int id) {
        Function_Role oldFunction_Role = function_roleRepository.findById(id).get();
        oldFunction_Role.setIdFunctionRole(function_role.getIdFunctionRole());
        oldFunction_Role.setFunction(function_role.getFunction());
        oldFunction_Role.setRole(function_role.getRole());
        function_roleRepository.save(function_role);
        return function_role;
    }


}
