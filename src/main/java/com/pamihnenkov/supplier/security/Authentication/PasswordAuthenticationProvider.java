package com.pamihnenkov.supplier.security.Authentication;


import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component("passwordAuthenticationProvider")
public class PasswordAuthenticationProvider implements AuthenticationProvider {


    private final ApplicationUserService applicationUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordAuthenticationProvider(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = applicationUserService.loadUserByUsername(login);

        if (passwordEncoder.matches(password,user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(login,password,user.getAuthorities());
        } else{
            throw new BadCredentialsException ("Неправильное сочетание логина и пароля.");
            }
        }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
