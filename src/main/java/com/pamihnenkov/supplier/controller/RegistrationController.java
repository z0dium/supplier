package com.pamihnenkov.supplier.controller;


import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    private final ApplicationUserService applicationUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
//        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
//    }

    @GetMapping("/registration*")
    public ModelAndView showCRegistrationForm(@ModelAttribute ApplicationUser applicationUser) {
        ModelAndView mav = new ModelAndView("registration");
        System.out.println(applicationUser);

        if(applicationUser==null){
            applicationUser = new ApplicationUser();
        }

        mav.addObject("applicationUser", applicationUser);
        System.out.println(mav);
        return mav;
    }

    @PostMapping("/registration*")
    public ModelAndView processRegistration(@ModelAttribute ApplicationUser applicationUser){
        ModelAndView mav = new ModelAndView();
        applicationUser.getAuthorities().add(ApplicationGrantedAuthority.ROLE_USER);
        applicationUser.setAccountNonExpired(true);
        applicationUser.setAccountNonLocked(true);
        applicationUser.setCredentialsNonExpired(true);
        applicationUser.setEnabled(true); //TO DO implement email verification.

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));

        try {
            if (applicationUserService.save(applicationUser).getId() != null) {
                mav.setViewName("redirect:/login");

            }else mav.setViewName("redirect:/registration?error=true");
            return mav;
        }catch (Exception ex){
            // do smth
            mav.addObject("applicationUser", applicationUser);
            mav.addObject("message","Данный email уже используется другим пользователем.");
            mav.setViewName("registration");
            return mav;
        }
    }
}
