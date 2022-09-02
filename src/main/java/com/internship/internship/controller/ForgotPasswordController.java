package com.internship.internship.controller;

import com.internship.internship.entity.User;
import com.internship.internship.entity.UserNotFoundException;
import com.internship.internship.entity.Utility;
import com.internship.internship.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @GetMapping("/forgot_password")
    public ModelAndView showForgotPasswordForm() {
        ModelAndView mav = new ModelAndView("user/forget_password/forget_password_form");
        mav.addObject("pagetitle", "forgetpassword");
        return mav;

    }


    @PostMapping("/forgot_password")
    public ModelAndView processForgotPassword(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/forget_password/forget_password_form");
       String email =request.getParameter("email");
       String token= RandomString.make(255);
     //   System.out.println("Email: " + email +"\n");
      //  System.out.println("token is : " + token +"\n");


        try {
            userService.updateResetPasswordToken(token,email);
            //generate reset password link based on token
            String resetPassworkLink = Utility.getSiteURL(request)+ "http://localhost:8081/"+"reset_password?token="+ token ;

            System.out.printf("\n ---------" +
                    "the reset passowrd lin is::::" +
                    resetPassworkLink);
            //send email to the user
            sendEmail(email , resetPassworkLink);
            mav.addObject("message","Email have sent succussflutly, please check your email  ");
        } catch (UserNotFoundException e) {
            mav.addObject("error", e.getMessage());
        } catch ( UnsupportedEncodingException |MessagingException e ) {
            mav.addObject("error", "Error while sending emaill" );
        }

        mav.addObject("pagetitle", "forget password");
        return mav;
    }

    public void sendEmail(String email, String resetPassworkLink ) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("timoumimahmoud616@gmail.com", "Admin ");
        helper.setTo(email);
        String subject= "here is the link to reset your password";
        String content ="<h2 style=\" color: red;\"> Hello , </h2> "+
                "<p> you have requested to change your password </p> "
                +"<p> Click the link below to change </p>"
                +"<p>  <a href=\""+resetPassworkLink+"\"> Change my password</a> </p>"
                +"<p>  ignore this email if you do remember you password </p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public ModelAndView showResetPasswordForm(@Param(value = "token") String token) {
        ModelAndView mav = new ModelAndView("user/forget_password/reset_password_form");
        User user = userService.getByResetPasswordToken(token);
        mav.addObject("token", token);

     /*   if (user == null) {
            ModelAndView mavMessage = new ModelAndView("fragments/message.html");

            mavMessage.addObject("title", "Reset your password");
            mavMessage.addObject("message", "Invalid Token");

            return mavMessage;
        }*/

        mav.addObject("token", token);
        mav.addObject("pagetitle", "Reset your password ");

       return  mav;
    }

    @PostMapping("/reset_password")
    public ModelAndView  processResetPassword(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/fragments/message");
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        mav.addObject("title", "Reset your password");

        if (user == null) {
            ModelAndView mavMessage = new ModelAndView("fragments/message.html");

            mavMessage.addObject("title", "Reset your password");
            mavMessage.addObject("message", "Invalid Token");

            return mavMessage;

        } else {
            userService.updatePassword(user, password);

            mav.addObject("message", "You have successfully changed your password.");
        }
        return  mav;
    }
}