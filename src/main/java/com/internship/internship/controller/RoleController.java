package com.internship.internship.controller;


import com.internship.internship.entity.Role;
import com.internship.internship.repository.RoleRepository;
import com.internship.internship.services.FunctionService;
import com.internship.internship.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
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
/*  Normal Desplay
    @GetMapping("/listRole")
    public ModelAndView  RoleList()  {
        ModelAndView mav = new ModelAndView("role/list");
        mav.addObject("role",roleService.findAll() );
        return mav;
    }

 */

    /////////////////////////////////////////////////////////////////////
    @GetMapping("/listRole")
    public ModelAndView getAllPages(Model model){
        return getOnePage( 1);
    }


    @GetMapping("/listRole/page/{pageNumber}")
    public ModelAndView getOnePage( @PathVariable("pageNumber") int currentPage){
        Page<Role> page = roleService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Role> role = page.getContent();
        ModelAndView mav = new ModelAndView("role/list");

        mav.addObject("currentPage", currentPage);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("role", role);

        return mav;
    }
//////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/addRole")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("role/add");
        Role role = new Role();
        mav.addObject("role", role);
        mav.addObject("Function", functionService.findAll());
        return mav;
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public ModelAndView  saveDept(@Valid Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("role/add");
            mav.addObject("role", role);
            mav.addObject("Function", functionService.findAll());
            return mav;
        }
        roleService.save(role);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Role/listRole");
        return new ModelAndView(redirectView);
    }


//////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Role role = roleService.findBy(id);
        ModelAndView mav = new ModelAndView("role/update");
        mav.addObject("role", role);
        mav.addObject("Function", functionService.findAll());

        return mav;
    }
    @PostMapping(value = "/edit/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Role role ) {
        role.setId(id);
        roleRepository.save(role);
        ModelAndView mav = new ModelAndView("role/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Role/listRole");
        return new ModelAndView(redirectView);
    }

//////////////////////////////////////////////////////////////////////////////////

}
