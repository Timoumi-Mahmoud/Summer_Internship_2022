package com.example.Stage.controller;

import com.example.Stage.entity.*;
import com.example.Stage.repository.Function_RoleRepository;
import com.example.Stage.services.FunctionService;
import com.example.Stage.services.Function_RoleService;
import com.example.Stage.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Function_Role")
public class Function_RoleController {

    @Autowired
    private Function_RoleService function_roleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FunctionService functionService;
    @Autowired
    private Function_RoleRepository function_roleRepository;



    @GetMapping("/add")
    public ModelAndView AffectRole() {
        ModelAndView mav = new ModelAndView("function_role/giveFunction");
        Function_Role function_role =new Function_Role();
        mav.addObject("function_role", function_role);
        Map<String, List<Role>> departs = new HashMap<String, List<Role>>();
        mav.addObject("role", roleService.findAll());

        Map<String, List<Role>> ss = new HashMap<String, List<Role>>();
        mav.addObject("function", functionService.findAll());





        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveAffectedRole( Function_Role function_role ) {
        function_roleService.save(function_role);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/User/userList");
        return new ModelAndView(redirectView);
    }







}
