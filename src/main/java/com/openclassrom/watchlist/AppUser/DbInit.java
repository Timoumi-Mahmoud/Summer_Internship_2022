package com.openclassrom.watchlist.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class DbInit implements CommandLineRunner{


private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

public  DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder){
    this.userRepository=userRepository;
    this.passwordEncoder=passwordEncoder;

}
    @Override
    public void run(String... args) throws Exception {
     User dan=new User("mahmoud",passwordEncoder.encode("mahmoud123"),"USER", "");
     User admin=new User("admin",passwordEncoder.encode("admin123"), "ADMIN","access_abc1,access_abc2");
     User manager=new User("manager",passwordEncoder.encode("manager123"), "MANAGER","access_abc1");


        List<User> users= Arrays.asList(dan, admin,manager);
        userRepository.saveAll(users);



    }
}
