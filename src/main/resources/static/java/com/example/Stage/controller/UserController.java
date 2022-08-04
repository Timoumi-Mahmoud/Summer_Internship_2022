package com.example.Stage.controller;

import com.example.Stage.entity.Department;
import com.example.Stage.entity.User;
import com.example.Stage.services.DepartmentService;
import com.example.Stage.services.UserService;
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
  private   UserService userService;

  @Autowired
  private DepartmentService departmentService;
/*
public UserController(UserService UserService){  same as autowired
}*/






    @RequestMapping(value = "/list")
    public List<User> userList() {
         userService.login("mahmoud@gmail", "timoumi");

        return userService.findAll();
    }

    @RequestMapping(value = "/list/{id}")
    public User findById(@PathVariable int id) {
        return userService.findBy(id);
    }


    @PostMapping(value = "/list/new")
    public User createNewUser(@RequestBody User user){
        return   userService.save(user);
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
            userService.login("gmail@gmail.com", "gmail");
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
