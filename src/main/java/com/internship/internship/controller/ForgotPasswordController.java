package com.internship.internship.controller;

import com.internship.internship.entity.User;
import com.internship.internship.entity.password.UserNotFoundException;
import com.internship.internship.entity.password.Utility;
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


        String con="\n" +
                "<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Reset Password Email Template</title>\n" +
                "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {text-decoration: underline !important;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "    <!--100% body table-->\n" +
                "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                          <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\n" +
                "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\n" +
                "                          </a>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding:0 35px;\">\n" +
                "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                "                                            requested to reset your password</h1>\n" +
                "                                        <span\n" +
                "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "                                            We cannot simply send you your old password. A unique link to reset your\n" +
                "                                            password has been generated for you. To reset your password, click the\n" +
                "                                            following link and follow the instructions.\n" +
                "                                        </p>\n"
                +"<p>  <a href=\""+resetPassworkLink+"\"> Change my password</a> </p>"+
                "<br>"+
/*
 "                                        <a href=\""+resetPassworkLink+ "\n" +
                "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\n" +
                "                                            Password</a>\n" +
 */

                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.ST2i.com</strong></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";



        helper.setSubject(subject);
        helper.setText(con,true);
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
        ModelAndView mav = new ModelAndView("user/forget_password/message");
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        mav.addObject("title", "Reset your password");

        if (user == null) {
            ModelAndView mavMessage = new ModelAndView("user/forget_password/message.html");

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