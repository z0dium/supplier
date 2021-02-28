package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.model.commandObjects.User.UserIdAndFioCom;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;
    private final OrganizationService organizationService;

    public AdminController(ApplicationUserService applicationuserService, DepartmentService departmentService, OrganizationService organizationService) {
        this.applicationUserService = applicationuserService;
        this.departmentService = departmentService;
        this.organizationService = organizationService;
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

    @GetMapping("/admin/users/{userId}")
    public ModelAndView showAndEditUser(@PathVariable Long userId){

        ModelAndView mav = new ModelAndView();
        ApplicationUser user = applicationUserService.findById(userId);

        if (user != null) {
            mav.addObject("applicationUser", user);
            mav.addObject("organizations", user.getOrganizations());
            mav.addObject("listOfOrganizations", organizationService.findAll());
            mav.setViewName("editUser");
            return mav;
        }

        mav.setViewName("errorHandler");
        return mav;
    }

    @PostMapping("/admin/users/{userId}/addOrganization")
    @Transactional
    public String addOrganization(@RequestParam("innCode") String innCode, @PathVariable Long userId){
        ApplicationUser user = applicationUserService.findById(userId);
        user.getOrganizations().add(organizationService.findByInnCode(innCode));
        return "redirect:/admin/users/"+ userId;
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

    @GetMapping("/admin/departments/create")
    public ModelAndView createNewDepartment(){
        ModelAndView mav = new ModelAndView();
        Department department = new Department();

        Iterable<UserIdAndFioCom> list = applicationUserService.findAllManagedUsers().stream()
                .map(UserIdAndFioCom::new)
                .collect(Collectors.toList());
        Iterable<Organization> listOfOrganizations = organizationService.findAllManaged();
        mav.addObject("department",department);
        mav.addObject("listOfOrganizations",listOfOrganizations);
        mav.addObject("listOfUsers", list);
        mav.setViewName("editDepartment");
        return  mav;
    }

    @GetMapping("/admin/departments/{id}")
    public ModelAndView showAndEditDepartment (@PathVariable Long id){

        ModelAndView mav = new ModelAndView();
        Department department = departmentService.findById(id);

        if (department != null) {

            List<UserIdAndFioCom> list = applicationUserService.findAllManagedUsers().stream()
                    .map(UserIdAndFioCom::new)
                    .collect(Collectors.toList());
            mav.addObject("department", department);
            mav.addObject("listOfUsers", list);
            mav.setViewName("editDepartment");
            return mav;
        }

        mav.setViewName("errorHandler");
        return mav;
    }

    @PostMapping("/admin/departments/save")
    public String saveDepartment(@ModelAttribute Department department){
        Long id = department.getId();
        if (id==null){
            department.setOrganization(organizationService.findById(department.getOrganization().getId()));
            departmentService.save(department);
        } else {
            Department persist =  departmentService.findById(id);
            persist.setLeader(applicationUserService.findById(department.getLeader().getId()));
            persist.setSupplier(applicationUserService.findById(department.getSupplier().getId()));
            departmentService.save(persist);
        }

        return "redirect:/admin/departments";
    }
}
