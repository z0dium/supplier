package com.pamihnenkov.supplier.config;

import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.security.Authentication.PasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordAuthenticationProvider passwordAuthenticationProvider;
    private final ApplicationUserService userDetailsService;

    @Autowired
    public ApplicationSecurityConfig(PasswordAuthenticationProvider passwordAuthenticationProvider, ApplicationUserService userDetailsService) {
        this.passwordAuthenticationProvider = passwordAuthenticationProvider;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/css/*","/js/*","/images/*").permitAll()
                    .antMatchers("/h2-console/*").permitAll()
                    .antMatchers("/registration","/registration/").anonymous()
                    .antMatchers("/requests/create").hasRole("ADMIN")
                    .antMatchers("/supplier","/supplier/").hasRole("SUPPLIER")
                    .anyRequest().authenticated().and()
                .formLogin().and()
                .rememberMe().userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordAuthenticationProvider);
    }


}
