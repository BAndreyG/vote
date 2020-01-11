package ru.javawebinar.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javawebinar.vote.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder managerBuilder) throws  Exception{
        System.out.println(managerBuilder);
        managerBuilder.userDetailsService(service);
    }
   /* @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password") // Spring Security 5 requires specifying the password storage format
                .roles("USER");
    }*/
/*
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
                .antMatchers("/api/v1/restorans/**").hasRole("ADMIN")
                .antMatchers("/votes/**").permitAll()//hasRole("USER")
                .antMatchers("api/v1/menus/*").permitAll()
                .anyRequest().authenticated();
                *//*.and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/votes").permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login");*//*
    }*/
}
