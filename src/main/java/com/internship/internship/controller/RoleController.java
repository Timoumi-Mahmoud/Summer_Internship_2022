package com.internship.internship.controller;


import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.Function;
import com.internship.internship.entity.Role;
import com.internship.internship.entity.User;
import com.internship.internship.repository.RoleRepository;
import com.internship.internship.services.FunctionService;
import com.internship.internship.services.RoleService;
import com.internship.internship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value="/Role")
public class RoleController {

@Autowired
private FunctionService functionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("delete/{id}")
    public RedirectView  remove( @PathVariable int id){
        roleService.delete(id);
        return new RedirectView("/Role/listRole");
    }

    @GetMapping("/listRole")
    public ModelAndView  RoleList()  {
        ModelAndView mav = new ModelAndView("role/list");
        mav.addObject("role",roleService.findAll() );
        return mav;
    }


    ////////////////////////
    @GetMapping("/addRole")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("role/add");
        Role role = new Role();
        mav.addObject("role", role);
        mav.addObject("Function", functionService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveRole",method = RequestMethod.POST)
    public ModelAndView  saveDept(@Valid Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("role/add");
        }
        roleService.save(role);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Role/listRole");
        return new ModelAndView(redirectView);
    }




    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Role role = roleService.findBy(id);
        ModelAndView mav = new ModelAndView("role/update");
        mav.addObject("role", role);
        return mav;
    }
    @PostMapping(value = "/update/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Role role ) {
        role.setId(id);
        roleRepository.save(role);
        ModelAndView mav = new ModelAndView("role/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Role/listRole");
        return new ModelAndView(redirectView);
    }


}
