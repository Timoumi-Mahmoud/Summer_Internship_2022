package com.internship.internship.controller;



import com.internship.internship.entity.Department;
import com.internship.internship.entity.Role;
import com.internship.internship.repository.DepartmentRepository;
import com.internship.internship.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/Department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @GetMapping("/list")
    public ModelAndView getAllPages(Model model){
        return getOnePage( 1);
    }


    @GetMapping("/list/page/{pageNumber}")
    public ModelAndView getOnePage( @PathVariable("pageNumber") int currentPage){
        Page<Department> page = departmentService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Department> departs = page.getContent();
        ModelAndView mav = new ModelAndView("department/list");

        mav.addObject("currentPage", currentPage);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("departs", departs);
        return mav;
    }

    @RequestMapping(value = "delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public RedirectView remove(@PathVariable int id) {
        departmentService.delete(id);
        return new RedirectView("/Department/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") int id) {
        Department department = departmentService.findBy(id);
        ModelAndView mav = new ModelAndView("department/update");
        mav.addObject("department", department);
        return mav;
    }
    @PostMapping(value = "/edit/{id}")
    public ModelAndView updateSave( @PathVariable("id") int id,@Valid Department department, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("department/update");

        }

        department.setIdDepartment(id);

        departmentRepository.save(department);
        ModelAndView mav = new ModelAndView("department/update");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Department/list");
        return new ModelAndView(redirectView);
    }

    @PostMapping(value = "/addDepartment" )
    public ModelAndView ajouter(Department department){
        departmentService.save(department);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Department/list");
        return new ModelAndView(redirectView);
    }

}




