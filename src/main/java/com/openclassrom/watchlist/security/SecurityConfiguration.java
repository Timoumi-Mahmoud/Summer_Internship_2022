package com.openclassrom.watchlist.security;


import com.openclassrom.watchlist.AppUser.UserPrensibleDetalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrensibleDetalsService userPrensibleDetalsService;


    public SecurityConfiguration (UserPrensibleDetalsService userPrensibleDetalsService){
        this.userPrensibleDetalsService=userPrensibleDetalsService;
    }
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProviderBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.


                    authorizeRequests()
                    /// .anyRequest().authenticated()
                    .antMatchers("/").permitAll()
                    .antMatchers("/").authenticated()
                    .antMatchers("/admin").hasRole("ADMIN")

                    .antMatchers("/users").hasRole("ADMIN")

                    .and().httpBasic();
    }



@Bean
    DaoAuthenticationProvider authenticationProviderBean(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    System.out.println("resssssssss"+passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
    return daoAuthenticationProvider;
    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
