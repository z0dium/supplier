package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private final ApplicationUserService applicationUserService;

    public AdminController(ApplicationUserService userService) {
        this.applicationUserService = userService;
    }

    @GetMapping("/admin")
    public String enterAdminPanel(){

        return "adminPanel";
    }

    @GetMapping("/admin/users")
    public String showAllUsers(Model model){

        model.addAttribute("users", applicationUserService.findAll());
        return "allUsers";
    }

    @GetMapping("/admin/users/{id}")
    public ModelAndView showUser(@PathVariable Long id){

        ModelAndView mav = new ModelAndView();
        ApplicationUser user = applicationUserService.findById(id);

        if (user != null) {
            mav.addObject("applicationUser", user);
            mav.setViewName("editUser");
            return mav;
        }

        mav.setViewName("errorHandler");
        return mav;
    }

    @PostMapping("/admin/users/save")
    public String saveUser(@ModelAttribute ApplicationUser applicationUser){
        ApplicationUser user = applicationUserService.findById(applicationUser.getId());

        user.setEnabled(applicationUser.isEnabled());
        user.setAccountNonLocked(applicationUser.isAccountNonLocked());

        applicationUserService.save(user);

        return "redirect:/admin";
    }

}
