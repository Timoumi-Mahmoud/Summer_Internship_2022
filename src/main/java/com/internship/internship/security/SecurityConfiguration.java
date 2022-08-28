package com.internship.internship.security;


import com.internship.internship.entity.Role;
import com.internship.internship.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;




    public SecurityConfiguration (MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProviderBean());
    }
  /*  public static  String s=  SecurityContextHolder.getContext().getAuthentication().getAuthorities()+"";;*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
             // .anyRequest().authenticated()
                    .antMatchers("/").permitAll()
                    .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                //      .antMatchers("/admin/**").hasRole("ADMIN")
                  //  .antMatchers("/manager").hasRole("MANAGER")
                   // .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                    .and().formLogin()
                    .loginProcessingUrl("/signin")
                    .loginPage("/login").permitAll()
                    .usernameParameter("txtUsername")
                    .passwordParameter("txtPassword")
                    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                    .and().rememberMe().tokenValiditySeconds(2592000).key("mySecret!").userDetailsService(userDetailsService)

           .and().exceptionHandling().accessDeniedPage("/accessDenied");

            ;
    }



@Bean
    DaoAuthenticationProvider authenticationProviderBean(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
    return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }






}
