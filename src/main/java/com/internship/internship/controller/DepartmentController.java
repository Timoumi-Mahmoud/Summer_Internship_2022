package com.internship.internship.controller;



import com.internship.internship.entity.Department;
import com.internship.internship.repository.DepartmentRepository;
import com.internship.internship.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;




    @GetMapping("/list")
    public ModelAndView deptList() {
        ModelAndView mav = new ModelAndView("department/list");
        mav.addObject("departs",departmentService.findAll() );
        return mav;
    }
/*
        @GetMapping("/list/{id}")
        public ModelAndView  listUserDepartment(@PathVariable Integer id) {
            String viewName = "department/listUser";

            System.out.println(departmentRepository.listUser(id));
            List a= departmentRepository.listUser(id);
            for (int i = 0; i < a.size(); i++) {
                System.out.println(a.get(i));
            }
            Map<String, List<String>> model = new HashMap<String, List<String>>();
            model.put("departs", departmentRepository.listUser(id));
            return new ModelAndView(viewName, model);
    }

*/
    @GetMapping("delete/{id}")
    public RedirectView remove(@PathVariable int id) {
        departmentService.delete(id);
        return new RedirectView("/Department/list");
    }


    @GetMapping("/add")
    public ModelAndView addDepForm() {
        ModelAndView mav = new ModelAndView("department/add");
        Department department = new Department();
        mav.addObject("department", department);
        mav.addObject("departs", departmentService.findAll());


        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveDept(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("department/add");
        }
            departmentService.save(department);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/Department/add");

            return new ModelAndView(redirectView);
        }




    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Department department = departmentService.findBy(id);
        ModelAndView mav = new ModelAndView("department/update");
        mav.addObject("department", department);
        return mav;


    }
    @PostMapping(value = "/update/{id}")
    public ModelAndView updateSave(@PathVariable("id") int id, Department department ) {
        department.setIdDepartment(id);
        departmentRepository.save(department);
        ModelAndView mav = new ModelAndView("department/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Department/add");
        return new ModelAndView(redirectView);
        }

    }




