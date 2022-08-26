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


  //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
     /* String s=  SecurityContextHolder.getContext().getAuthentication().getAuthorities()+"";
        String k=  "ADMIN";
        System.out.println("the value of kkkkk ::::::" +k+"\n   the new value");
        System.out.println(s.substring(1,s.length() - 1));
       String l=s.substring(1,s.length() - 1);
      String y= l.replace("ROLE_", "");*/



        return  "this admin page***!!!!!" + "\n ";

    }


    @GetMapping("/profile")
    public String profil() {

        return  "user profile pagellllllmmmmmm ";

    }


    @GetMapping("/manager")
    public String manager() {

        return  "this manager page***!!!!!";

    }

    @GetMapping("/abc1")
    public String abc() {

        return  "\n acces_abcOne \n!";

    }

    @GetMapping("/abc2")
    public String abc2() {

        return  "\n acces_abcTWO \n!";
    }


@GetMapping("/users")
public List<User> users(){
        return userRepository.findAll();}
/*

    class LoginController {
        @GetMapping("/login")
        String login() {
            return "login";
        }
    }*/



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
        /*

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("page-title", "Home");

        return mav;

    }*/

}
