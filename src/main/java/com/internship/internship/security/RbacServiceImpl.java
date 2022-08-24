package com.internship.internship.security;

import com.internship.internship.entity.Function;
import com.internship.internship.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rbacService")
public class RbacServiceImpl implements  RbacService {
@Autowired
    FunctionRepository functionRepository;
    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        boolean hasPermission = false;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();  //get the connected user username(+ other info like role , address...)
            //For reading all urls that user have access to it , with a query urlsFinder
            List<Function> urls = functionRepository.urlsFinder(username);

            System.out.println( "the secoodne test iss  ::  "+ urls +"\n");

            for (Function f : urls) {
               // System.out.println(functionRepository.urlsFinder(username));
                for (Function i : f.getChildren()) {

                    if (antPathMatcher.match( f.getUrl() +   i.getUrl(), request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }
        return hasPermission;
    }
}

