package com.internship.internship.entity;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request){
        String siteUrl = request.getRequestURI().toString();
        System.out.println("the site url is :: \n "+ siteUrl);
        return siteUrl.replace(request.getServletPath(),"");

    }


}
