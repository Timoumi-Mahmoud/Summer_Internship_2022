package com.internship.internship;

import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class adminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();}
    @GetMapping("/test")
    public ModelAndView addDepForm() {
        ModelAndView mav = new ModelAndView("test");
        return mav;
    }

    @GetMapping("/testTwo")
    public ModelAndView addDepFormTwo() {
        ModelAndView mav = new ModelAndView("testTwo");
        return mav;
    }
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    @GetMapping("/accessDenied")
    public ModelAndView getAccessDenied() {
        ModelAndView mav = new ModelAndView("accessDenied");
        return mav;

    }

}
