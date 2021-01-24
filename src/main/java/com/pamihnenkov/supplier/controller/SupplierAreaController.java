package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import com.pamihnenkov.supplier.service.DepartmentService;
import com.pamihnenkov.supplier.service.RequestService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class SupplierAreaController {

    private final ApplicationUserService applicationUserService;
    private final RequestService requestService;
    private final DepartmentService departmentService;

    public SupplierAreaController(ApplicationUserService applicationUserService, RequestService requestService, DepartmentService departmentService) {
        this.applicationUserService = applicationUserService;
        this.requestService = requestService;
        this.departmentService = departmentService;
    }

    @GetMapping("/supplier")
    public String showSupplierArea(){
  //      ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        return "supplierArea";
    }

    @GetMapping("/supplier/sib")
    public ModelAndView enterSibArea(){
        ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        RequestLinesContainer container = new RequestLinesContainer();
        container.setRequestLines(requestService.findByDepartmentIn(departmentService.findBySupplier(currentUser)).stream()
                .flatMap(request -> request.getRequestLines().stream())
                .collect(Collectors.toList()));
        ModelAndView mav = new ModelAndView();
        mav.addObject("container", container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @GetMapping("/checking")
    public ModelAndView checkNewRequests(){
        RequestLinesContainer container = new RequestLinesContainer();
        ModelAndView mav = new ModelAndView();
        mav.addObject("container", container);
        mav.setViewName("allRequestsLines");
        return mav;
    }
}