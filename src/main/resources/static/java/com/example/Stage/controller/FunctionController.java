package com.example.Stage.controller;

import com.example.Stage.entity.Function;
import com.example.Stage.repository.FunctionRepository;
import com.example.Stage.services.FunctionService;
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
@RequestMapping(value="/Function")
public class FunctionController {



    @Autowired
    private FunctionService functionService;

    @Autowired
    private FunctionRepository functionRepository;
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
    public RedirectView remove(@PathVariable int id){
        functionService.delete(id);
        return new RedirectView("/Function/list");
    }

    @GetMapping("/list")
    public ModelAndView RoleList()  {
        Map<String, List<Function>> model = new HashMap<String, List<Function>>();
        String viewName = "function/list";
        model.put("function",  functionService.findAll());
        return new ModelAndView(viewName , model);
    }


    ////////////////////////
    @GetMapping("/addFunction")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("function/add");
        Function function = new Function();
        mav.addObject("function", function);
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
        redirectView.setUrl("/Function/addFunction");
        return new ModelAndView(redirectView);
    }




    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Function function = functionService.findBy(id);
        ModelAndView mav = new ModelAndView("function/update");
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


