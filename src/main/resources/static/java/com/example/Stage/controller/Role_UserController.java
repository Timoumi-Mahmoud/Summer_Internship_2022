package com.example.Stage.controller;

import com.example.Stage.entity.Function;
import com.example.Stage.entity.Role;
import com.example.Stage.entity.Role_User;
import com.example.Stage.entity.User;
import com.example.Stage.repository.Role_UserRepository;
import com.example.Stage.services.RoleService;
import com.example.Stage.services.Role_UserService;
import com.example.Stage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Role_user")
public class Role_UserController {


    @Autowired
    private Role_UserService role_userService;

    @Autowired
    private Role_UserRepository role_userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
/*
    @RequestMapping(value = "/list")
    public List<Role_User> userList() {return role_userService.findAll();}

    @RequestMapping(value = "/list/{id}")
    public Role_User findById(@PathVariable int id) {
        return role_userService.findBy(id);
    }


    @PostMapping(value = "/list/new")
    public Role_User createNewUser(@RequestBody Role_User role_user){
        return   role_userService.save(role_user);
    }

    @PostMapping(value = "/update/{id}")
    public Role_User update(@RequestBody Role_User role_user, @PathVariable int id){
        return   role_userService.update(role_user, id);}

    @DeleteMapping(value = "delete/{id}")
    public void Delete( @PathVariable int id){
        role_userService.delete(id);
    }
*/

    @GetMapping("delete/{id}")
    public RedirectView  remove( @PathVariable int id){
        role_userService.delete(id);
        return new RedirectView("/Role_user/list");
    }



    @GetMapping("/list")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("role_user/list");
        List<Role_User> role_user =role_userRepository.findAll();
        mav.addObject("role_user", role_user);
        return mav;
    }




    @GetMapping("/add/{id}")
    public ModelAndView AffectRole(@PathVariable   Integer id ) {
        System.out.println("\n ---------");
        System.out.println(role_userRepository.findAll());
        System.out.println("\n ---------");

        User u =new User();
        u=userService.findBy(id);
        ModelAndView mav = new ModelAndView("role_user/giveRole");
        mav.addObject("u", u);
        Role_User role_user = new Role_User();
        mav.addObject("role_user", role_user);
        Map<String, List<Role>> departs = new HashMap<String, List<Role>>();
        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public ModelAndView saveAffectedRole( Role_User role_user ,@PathVariable("id") int id) {
        role_user.setRole_user(userService.findBy(id));
        System.out.println(role_user);
        role_userRepository.save(role_user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/User/userList");
        return new ModelAndView(redirectView);
    }

/*
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable   Integer id ) {
        User u =new User();
        u=userService.findBy(id);
        ModelAndView mav = new ModelAndView("role_user/giveRole");
        mav.addObject("u", u);
        Role_User role_user = new Role_User();
        mav.addObject("role_user", role_user);
        Map<String, List<Role>> departs = new HashMap<String, List<Role>>();
        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveUpdate/{id}", method = RequestMethod.POST)
    public ModelAndView saveDept( Role_User role_user ,@PathVariable("id") int id) {
        role_user.setRole_user(userService.findBy(id));
        System.out.println(role_user);
        role_userRepository.save(role_user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/User/userList");
        return new ModelAndView(redirectView);
    }
*/


}
