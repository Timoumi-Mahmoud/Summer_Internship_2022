package com.internship.internship.controller;


import com.internship.internship.entity.Department;
import com.internship.internship.entity.Function;
import com.internship.internship.repository.FunctionRepository;
import com.internship.internship.services.FunctionService;
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

    @GetMapping("delete/{id}")
    public RedirectView remove(@PathVariable int id){
        functionService.delete(id);
        return new RedirectView("/Function/list");
    }

    @GetMapping("/list")
    public ModelAndView RoleList()  {
        ModelAndView mav = new ModelAndView("function/list");
        mav.addObject("function",  functionService.findAll());
     //  mav.addObject("ff",functionService.findBy(3).getNameFunction()     );


        return mav;
    }

    ////////////////////////
    @GetMapping("/addFunction")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("function/add");
        Function function = new Function();
        mav.addObject("function", function);
        mav.addObject("functionMother", functionService.findAll());
        mav.addObject("fun", functionService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveFunction",method = RequestMethod.POST)
    public ModelAndView  saveDept(@Valid Function function , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("function/add");
        }
        functionService.save(function);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Function/list");
        return new ModelAndView(redirectView);
    }
    //update
    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Function function = functionService.findBy(id);
        ModelAndView mav = new ModelAndView("function/update");
        mav.addObject("functionMother", functionService.findAll());

        mav.addObject("function", function);
        return mav;
    }
    @PostMapping(value = "/update/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Function function ) {
        function.setIdFunction(id);
        functionRepository.save(function);
        ModelAndView mav = new ModelAndView("function/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Function/list");
        return new ModelAndView(redirectView);
    }

}


