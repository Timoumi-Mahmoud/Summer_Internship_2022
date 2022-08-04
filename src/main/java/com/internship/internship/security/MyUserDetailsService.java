package com.internship.internship.security;

import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.AppUser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(s);
        MyUserDetails myUserDetails =new MyUserDetails(user);
        return myUserDetails;
    }
}
