package ru.javawebinar.vote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password") // Spring Security 5 requires specifying the password storage format
                .roles("USER");
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
                .antMatchers("/api/v1/restorans/**").hasRole("ADMIN")
                .antMatchers("/votes/**").permitAll()//hasRole("USER")
                .antMatchers("/api/v1/menus/**").permitAll()
                .anyRequest().authenticated();
                /*.and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/votes").permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login");*/
    }
}
