package com.openclassrom.watchlist.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .inMemoryAuthentication()
            .withUser("admin").
            password(passwordEncoder().encode("admin123"))
            .roles("ADMIN").authorities("access_abc1", "access_abc2").
            and().

            withUser("mahmoud").
            password(passwordEncoder().encode("mahmoud123"))
            .roles("USER").

            and()

            .withUser("manager").
            password(passwordEncoder().encode("manager123"))
            .roles("MANAGER").authorities("access_abc1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.
                    authorizeRequests()
                   /// .anyRequest().authenticated()
                    .antMatchers("/").permitAll()
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/manager/**").hasAuthority("access_abc1")
                    .antMatchers("/abc1").hasAuthority("access_abc1")
                    .antMatchers("/abc2").hasAuthority("access_abc2")

                    .and().httpBasic();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
