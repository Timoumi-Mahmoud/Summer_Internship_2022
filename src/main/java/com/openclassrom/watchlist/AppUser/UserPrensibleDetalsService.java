package com.openclassrom.watchlist.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrensibleDetalsService implements UserDetailsService {
@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(s);
        UserPrincipal userPrincipal=new UserPrincipal(user);
        return userPrincipal;
    }
}
