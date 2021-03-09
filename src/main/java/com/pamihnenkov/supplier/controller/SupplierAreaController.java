package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Persistence;
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
    public String showSupplierArea(){
  //      ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        return "supplierArea";
    }

    @GetMapping("supplier/{org}")
    public ModelAndView enterSibArea(@PathVariable String org){

        RequestLinesContainer container = new RequestLinesContainer();
        Long id = null;
        if (org.equals("sib")) id = 1L;
        if (org.equals("gbi")) id = 2L;
        Long finalId = id;
        container.setRequestLines(requestService.findByDepartmentIn(departmentService.findAll().stream()
                .filter(department -> department.getOrganization().getId()== finalId)  // TODO remove hardcoded Organization id
                .collect(Collectors.toSet())).stream()
                .flatMap(request -> request.getRequestLines().stream())
                .collect(Collectors.toList()));

        ModelAndView mav = new ModelAndView();
        mav.addObject("container", container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @GetMapping("supplier/checking")
    public ModelAndView checkNewRequests(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("requests", requestService.findAllUnchecked());
        mav.setViewName("allRequests");
        return mav;
    }
}
