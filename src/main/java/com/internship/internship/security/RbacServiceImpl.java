package com.internship.internship.security;

import com.internship.internship.entity.Function;
import com.internship.internship.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component("rbacService")
public class RbacServiceImpl implements  RbacService {
@Autowired
    FunctionRepository functionRepository;
    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        AtomicBoolean hasPermission = new AtomicBoolean(false);

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();  //get the connected user username(+ other info like role , address...)
            //For reading all urls that user have access to it , with a query urlsFinder
            List<Function> urls = functionRepository.urlsFinder(username);


            Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
            List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
            listAuthorities.addAll(authorities);
//getting the list of Roles of the connected user
            List<String> terms = new ArrayList<String>();
            for (int index = 0; index < listAuthorities.size(); index++) {

                String name = String.valueOf(listAuthorities.get(index)).replace("ROLE_", "");

                terms.add(name);
            }

//Loop over the Function list that givin after the SQL query findURL => get all the father urls
            for (Function f : urls) {
                //Loop over child Function
                for (Function i : f.getChildren()) {
                    //convert child Function sets of Roles (getRolesF()) and use Java_Stream to only pass the children tha have
                    //Roles in list of roles of  the connected  user
                    List<String> roleFunctionOfChildren = i.getRolesF().stream().map(p -> p.getName()).collect(Collectors.toList());
                    final  ArrayList<String> similar = new ArrayList<String>();
                    roleFunctionOfChildren.forEach(des -> {
                        Optional<String> opt = terms.stream().filter(met -> des.contains(met)).findFirst();
                        if (opt.isPresent()) {
                            similar.add(opt.get());
                    if (antPathMatcher.match( f.getUrl() +   i.getUrl(), request.getRequestURI())) {
                        hasPermission.set(true);
                    }
                        }
                    }); }
            }
        }
        return hasPermission.get();
    }
}

