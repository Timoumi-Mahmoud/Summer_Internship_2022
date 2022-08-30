package com.internship.internship.entity;

import com.internship.internship.security.MyUserDetails;
import com.internship.internship.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class AuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //   return Optional.ofNullable("Mahmoud").filter(s -> !s.isEmpty());    }
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();//return get the authenticated user information

        String username = authentication.getName();
        return Optional.ofNullable(username).filter(s -> !s.isEmpty());  //return the username
    }
    }

