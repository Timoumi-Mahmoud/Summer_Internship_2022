package com.internship.internship.controller;

import com.internship.internship.entity.Department;
import com.internship.internship.entity.Function;
import com.internship.internship.entity.Role;
import com.internship.internship.repository.FunctionRepository;
import com.internship.internship.repository.RoleRepository;
import com.internship.internship.services.FunctionService;
import com.internship.internship.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value="/Function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private RoleService roleService;
    @GetMapping("delete/{id}")
    public RedirectView remove(@PathVariable int id){
        System.out.println("\n -------------------------\n");
        System.out.println("this is the function by id \n"+ functionService.findBy(id)   +"\n");
        System.out.println("\n -------------------------\n");
        Function f= functionService.findBy(id);
        if(f.getParent() ==null){
            functionService.delete(id);
        }else {
            functionRepository.deleteByParentId(id);
        }
        return new RedirectView("/Function/list");
    }

    @GetMapping("/list")
    public ModelAndView RoleList()  {
        ModelAndView mav = new ModelAndView("function/list");
        mav.addObject("function",  functionService.findAll());
        //  mav.addObject("ff",functionService.findBy(3).getNameFunction()     );


        return mav;
    }

    @GetMapping("/addFunction")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("function/add");
        Function function = new Function();
        mav.addObject("function", function);
        mav.addObject("functionMother", functionService.findAll());
        mav.addObject("funRole", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/addFunction",method = RequestMethod.POST)
    public ModelAndView  saveDept(@Valid Function function , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("function/add");
        }
        functionService.save(function);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Function/list");
        return new ModelAndView(redirectView);
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Function function = functionService.findBy(id);
        ModelAndView mav = new ModelAndView("function/update");
        mav.addObject("functionMother", functionService.findAll());
        mav.addObject("funRole", roleService.findAll());
        mav.addObject("function", function);
        return mav;
    }
    @PostMapping(value = "/edit/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Function function, Role role ) {
        function.setIdFunction(id);
        functionRepository.save(function);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("function/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Function/list");
        return new ModelAndView(redirectView);
    }

}
