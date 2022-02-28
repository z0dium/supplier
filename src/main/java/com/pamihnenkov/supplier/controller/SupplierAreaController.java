package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class SupplierAreaController {

    private final ApplicationUserService applicationUserService;
    private final RequestService requestService;
    private final DepartmentService departmentService;

    public SupplierAreaController(ApplicationUserService applicationUserService, RequestService requestService, DepartmentService departmentService) {
        this.applicationUserService = applicationUserService;
        this.requestService = requestService;
        this.departmentService = departmentService;
    }

    @GetMapping("supplier")
    public String showSupplierArea(Model model){
        model.addAttribute("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
  //      ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return "supplierArea";
    }

    @GetMapping("supplier/checking")
    public ModelAndView checkNewRequests(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("requests", requestService.findAllUnchecked());
        mav.addObject("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
        mav.addObject("listOfDepartmentsForCurrentSupplier", departmentService.findBySupplier(applicationUserService.getCurrentUser()));
        mav.setViewName("allRequests");
        return mav;
    }
    @GetMapping("supplier/{org}")
    public ModelAndView enterSibArea(@PathVariable String org){
        Long id = null;
        if (org.equals("sib")) id = 1L;
        if (org.equals("gbi")) id = 2L;
        Long finalId = id;
        List<RequestLine> container = (requestService.findByDepartmentIn(departmentService.findAll().stream()
                .filter(department -> department.getOrganization().getId()== finalId)  // TODO remove hardcoded Organization id
                .collect(Collectors.toSet())).stream()
                .flatMap(request -> request.getRequestLines().stream())
                .collect(Collectors.toList()));

        ModelAndView mav = new ModelAndView();
        mav.addObject("container", container);
        mav.addObject("isNewRequestsExistsForSupplier",requestService.isNewRequestsExistsForSupplier());
        mav.setViewName("allRequestsLines");
        return mav;
    }


}
