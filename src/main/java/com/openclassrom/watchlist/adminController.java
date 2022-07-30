package com.openclassrom.watchlist;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@RestController
public class adminController {


    @GetMapping("/admin")
    public String admin() {

     return  "this admin page***!!!!!";

    }


    @GetMapping("/profile")
    public String profil() {

        return  "user profile pagellllllmmmmmm ";

    }


    @GetMapping("/manager")
    public String manager() {

        return  "this manager page***!!!!!";

    }

    @GetMapping("/abc/abc")
    public String abc() {

        return  "this api***abc!!!!!";

    }

}
