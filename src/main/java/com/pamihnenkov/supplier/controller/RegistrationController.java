package com.pamihnenkov.supplier.controller;


import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final ApplicationUserService applicationUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/registration")
    public String showCRegistrationForm(Model model) {

        model.addAttribute("applicationUser", new ApplicationUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute ApplicationUser applicationUser){

        applicationUser.getAuthorities().add(ApplicationGrantedAuthority.ROLE_USER);
        applicationUser.setAccountNonExpired(true);
        applicationUser.setAccountNonLocked(true);
        applicationUser.setCredentialsNonExpired(true);
        applicationUser.setEnabled(true); //TO DO implement email verification.

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));


        if (applicationUserService.save(applicationUser).getId()!= null) return "redirect:/";
        else return "registration/error";
    }
}
