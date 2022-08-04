package com.internship.internship.controller;


import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.Role;
import com.internship.internship.entity.User;
import com.internship.internship.repository.RoleRepository;
import com.internship.internship.services.RoleService;
import com.internship.internship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Role")
public class RoleController {


    @Autowired
   private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;
/*
    @RequestMapping(value = "/list")
    public List<Role> userList() {return roleService.findAll();}

    @RequestMapping(value = "/list/{id}")
    public Role findById(@PathVariable int id) {
        return roleService.findBy(id);
    }

    @PostMapping(value = "/list/new")
    public Role createNew(@RequestBody Role role){
        return   roleService.save(role);
    }

    @PostMapping(value = "/update/{id}")
    public Role update(@RequestBody Role role, @PathVariable int id){
        return   roleService.update(role, id);}

*/
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



    @RequestMapping("/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Integer userId,
                             @PathVariable Integer roleId){
        roleService.assignUserRole(userId, roleId);
        return "redirect:/user/Edit/"+userId;
    }




    @GetMapping("/AffectRole/{id}")
    public ModelAndView AffectRole(@PathVariable   Integer id ) {
        System.out.println("\n ---------");
        System.out.println(roleRepository.findAll());
        System.out.println("\n ---------");

        User u =new User();
        u=userService.findBy(id);
        ModelAndView mav = new ModelAndView("role_user/giveRole");
        mav.addObject("u", u);
        Role role_user = new Role();
        mav.addObject("role_user", role_user);
        mav.addObject("roles", roleService.findAll());
        return mav;
    }


    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public ModelAndView saveAffectedRole( Role role_user ,@PathVariable("id") int id) {
     //   role_user.setId;    userService.findBy(id));
        System.out.println(role_user);
        roleRepository.save(role_user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/User/userList");
        return new ModelAndView(redirectView);
    }













}
