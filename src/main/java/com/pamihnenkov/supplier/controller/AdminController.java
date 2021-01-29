package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.commandObjects.User.UserIdAndFioCom;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;

    public AdminController(ApplicationUserService applicationuserService, DepartmentService departmentService) {
        this.applicationUserService = applicationuserService;
        this.departmentService = departmentService;
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
    public ModelAndView showAndEditUser(@PathVariable Long id){

        ModelAndView mav = new ModelAndView();
        ApplicationUser user = applicationUserService.findById(id);

        if (user != null) {
            mav.addObject("applicationUser", user);
            mav.addObject("organizations", user.getOrganizations());
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

    @GetMapping("/admin/departments")
    public String showAllDepartments(Model model){

        model.addAttribute("departments", departmentService.findAll());
        return "allDepartments";
    }

    @GetMapping("/admin/departments/{id}")
    public ModelAndView showAndEditDepartment (@PathVariable Long id){

        ModelAndView mav = new ModelAndView();
        Department department = departmentService.findById(id);

        if (department != null) {
            mav.addObject("department", department);
            List<UserIdAndFioCom> list = applicationUserService.findByOrganizationId(department.getOrganization().getId()).stream()
                    .map(UserIdAndFioCom::new)
                    .collect(Collectors.toList());

            mav.addObject("listOfUsers", list);
            mav.setViewName("editDepartment");
            return mav;
        }

        mav.setViewName("errorHandler");
        return mav;
    }

    @PostMapping("/admin/departments/save")
    public String saveDepartment(@ModelAttribute Department department){

        departmentService.save(department);

        return "redirect:/admin/departments";
    }
}
