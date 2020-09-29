package com.pamihnenkov.supplier.config;

import com.pamihnenkov.supplier.security.Authentication.PasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordAuthenticationProvider passwordAuthenticationProvider;

    @Autowired
    public ApplicationSecurityConfig(PasswordAuthenticationProvider passwordAuthenticationProvider) {
        this.passwordAuthenticationProvider = passwordAuthenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/login/**","/css/*","/js/*").permitAll()
                    .antMatchers("/h2-console/*").permitAll()
                    .antMatchers("/requests/create").hasRole("ADMIN")
                    .anyRequest().authenticated().and()
                .formLogin();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordAuthenticationProvider);
    }


}
