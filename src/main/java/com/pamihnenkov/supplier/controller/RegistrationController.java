package com.pamihnenkov.supplier.controller;


import com.pamihnenkov.supplier.email.EmailService;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    private final ApplicationUserService applicationUserService;
    private final EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationController(ApplicationUserService applicationUserService, EmailService emailService) {
        this.applicationUserService = applicationUserService;
        this.emailService = emailService;
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
//        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
//    }

    @GetMapping("/registration")
    public ModelAndView showCRegistrationForm(@ModelAttribute ApplicationUser applicationUser) {
        ModelAndView mav = new ModelAndView("registration");

        if(applicationUser==null){
            applicationUser = new ApplicationUser();
        }

        mav.addObject("applicationUser", applicationUser);
        return mav;
    }

    @PostMapping("/registration")
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
                emailService.send("sib-centr@gmail.com",
                                    applicationUser.getEmail(),
                                "Регистрация на портале закупок",
                        "Спасибо за регистрацию!");

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
