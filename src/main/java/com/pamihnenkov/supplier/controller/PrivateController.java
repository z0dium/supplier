package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class PrivateController {

    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;
    private final OrganizationService organizationService;

    public PrivateController(ApplicationUserService applicationUserService, DepartmentService departmentService, OrganizationService organizationService) {
        this.applicationUserService = applicationUserService;
        this.departmentService = departmentService;
        this.organizationService = organizationService;
    }

    @GetMapping("private")
    public String openWorkspace(Model model){


        return "private";
    }

    @GetMapping("private/edit")
    public String editProfile (Model model){

        ApplicationUser user = applicationUserService.getCurrentUser();

        model.addAttribute("applicationUser",user);
        return "editUser";
    }
}
