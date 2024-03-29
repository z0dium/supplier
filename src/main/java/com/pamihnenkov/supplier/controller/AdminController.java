package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Organization;
import com.pamihnenkov.supplier.model.commandObjects.User.UserIdAndFioCom;
import com.pamihnenkov.supplier.security.ApplicationGrantedAuthority;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.OrganizationService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class AdminController {

    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;
    private final OrganizationService organizationService;

    public AdminController(ApplicationUserService applicationuserService, DepartmentService departmentService, OrganizationService organizationService) {
        this.applicationUserService = applicationuserService;
        this.departmentService = departmentService;
        this.organizationService = organizationService;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin")
    public String enterAdminPanel(){
        return "adminPanel";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin/users")
    public String showAllUsers(Model model){

        model.addAttribute("users", applicationUserService.findAll());
        return "allUsers";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin/users/{userId}")
    public ModelAndView showAndEditUser(@PathVariable Long userId){

        ModelAndView mav = new ModelAndView();
        ApplicationUser user = applicationUserService.findById(userId);

        if (user != null) {
            mav.addObject("applicationUser", user);
            mav.setViewName("editUser");
            return mav;
        }

        mav.setViewName("errorHandler");
        return mav;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("admin/users/{userId}/addOrganization")
    @Transactional
    public String addOrganization(@RequestParam("innCode") String innCode, @PathVariable Long userId){
        ApplicationUser user = applicationUserService.findById(userId);
        user.getOrganizations().add(organizationService.findByInnCode(innCode));
        return (user.getId().equals(applicationUserService.getCurrentUser().getId()))?
                "redirect:/app/private/edit" :
                "redirect:/app/admin/users/"+ userId;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("admin/users/{userId}/addRole")
    @Transactional
    public String addRole(@RequestParam("role") ApplicationGrantedAuthority role, @PathVariable Long userId){
        ApplicationUser user = applicationUserService.findById(userId);
        user.getAuthorities().add(role);
        return "redirect:/app/admin/users/"+ userId;
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("admin/users/save")
    public String saveUser(@ModelAttribute ApplicationUser applicationUser){
        ApplicationUser user = applicationUserService.findById(applicationUser.getId());

        user.setEnabled(applicationUser.isEnabled());
        user.setAccountNonLocked(applicationUser.isAccountNonLocked());

        applicationUserService.save(user);

        return "redirect:/app/admin";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @Transactional
    @PostMapping("admin/users/deleteRole/{roleToDelete}")
    public String saveUser(@ModelAttribute ApplicationUser applicationUser, @PathVariable ApplicationGrantedAuthority roleToDelete){
        ApplicationUser user = applicationUserService.findById(applicationUser.getId());
        user.getAuthorities().remove(roleToDelete);
        applicationUserService.save(user);

        return "redirect:/app/admin/users/" + applicationUser.getId();
    }


    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin/departments")
    public String showAllDepartments(Model model){

        model.addAttribute("departments", departmentService.findAll());
        return "allDepartments";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin/departments/create")
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

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("admin/departments/{id}")
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

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("admin/departments/save")
    public ModelAndView saveDepartment(@ModelAttribute Department department){
        Long id = department.getId();
        ModelAndView mav = new ModelAndView("redirect:/app/admin/departments");
        if (id==null){
            department.setOrganization(organizationService.findById(department.getOrganization().getId()));
            Optional<Department> dep = departmentService.findByNameAndOrganization(department.getName(),department.getOrganization());
            if ( dep.isEmpty()){
                departmentService.save(department);
                mav.addObject("message","Отдел '" + department.getName() + "' добавлен.");
            }
            else {  mav.addObject("message","Отдел с названием '" +  department.getName() + "'уже зарегистрирован в системе.");
                    mav.setViewName("redirect:/app/admin/departments/create");}
        }else {
            Department persist =  departmentService.findById(id);
            persist.setLeader(applicationUserService.findById(department.getLeader().getId()));
            persist.setSupplier(applicationUserService.findById(department.getSupplier().getId()));
            departmentService.save(persist);
        }
        return mav;
    }
}
