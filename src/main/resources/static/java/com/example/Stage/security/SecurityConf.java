package com.example.Stage.security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
@EnableWebSecurity
public class SecurityConf  extends WebSecurityConfigurerAdapter {

    private final String[] Public_Routes = {"Role/**", "/Department/addDepartment", "/User/addUser", "/User/saveUser", "/", "/Role/addRole"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.


                authorizeRequests()
                /// .anyRequest().authenticated()
                .antMatchers(Public_Routes).permitAll()
                .and()
                .httpBasic();
    }
}