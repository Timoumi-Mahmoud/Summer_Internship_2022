package com.internship.internship.controller;


import com.internship.internship.entity.Department;
import com.internship.internship.entity.User;
import com.internship.internship.services.DepartmentService;
import com.internship.internship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/User")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private DepartmentService departmentService;
/*
public UserController(UserService UserService){  same as autowired
}*/






    @RequestMapping(value = "/list")
    public List<User> userList() {

        return userService.findAll();
    }

    @RequestMapping(value = "/list/{id}")
    public User findById(@PathVariable int id) {
        return userService.findBy(id);
    }




    @PostMapping(value = "/update/{id}")
    public User update(@RequestBody User user, @PathVariable int id){
        return   userService.update(user, id);
    }


    @GetMapping("delete/{id}")
    public String  remove( @PathVariable int id){
        userService.delete(id);
        return "redirect:/";
    }





    @GetMapping( path={"/userList","/search"})

    public ModelAndView  userListall(String keyword)  {
        Map<String, List<User>> model = new HashMap<String, List<User>>();
        String viewName = "/user/userList";
        if(keyword != null){
            System.out.println("the  result is:: \n"+userService.search(keyword));
            model.put("Users",userService.search(keyword));
        }else {
            System.out.println("\n  -----------------\n");
            System.out.println("\n  -----------------\n");
            model.put("Users",  userService.findAll());
        }
        return new ModelAndView(viewName , model);
    }

    @GetMapping("/addUser")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("user/registration");
        User user = new User();
        mav.addObject("user", user);
        Map<String, List<Department>> departs = new HashMap<String, List<Department>>();
        mav.addObject("departs", departmentService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public ModelAndView  saveDept( User user) {
        userService.save(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);
    }

}
