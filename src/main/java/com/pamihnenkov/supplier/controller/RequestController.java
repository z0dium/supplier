package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.model.commandObjects.Department.DepartmentIdAndNameCom;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import com.pamihnenkov.supplier.service.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class RequestController {

    private final RequestService requestService;
    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;


    @Autowired
    public RequestController(RequestService requestService, UserService userService, ApplicationUserService applicationUserService, DepartmentService departmentService) {
        this.requestService = requestService;
        this.applicationUserService = applicationUserService;
        this.departmentService = departmentService;
    }

    @GetMapping("requests/create")
    public String showCreateForm(Model model) {

        RequestLinesContainer requestLinesContainer = new RequestLinesContainer();
        requestLinesContainer.getRequestLines().add(new RequestLine());
        model.addAttribute("requestLinesContainer", requestLinesContainer);
        Iterable<DepartmentIdAndNameCom> departments = departmentService.findByLeader(applicationUserService.getCurrentUser()).stream()
                                                    .map(DepartmentIdAndNameCom::new)
                                                    .collect(Collectors.toList());
        model.addAttribute("departments",departments);
        model.addAttribute("department",new Department());

        return "newRequest";
    }

    @PostMapping("requests/save")
    public String createRequest(@ModelAttribute RequestLinesContainer requestLinesContainer, @ModelAttribute Long departmentId) {
        Request newRequest = new Request();
        newRequest.setDate(new Date());

        newRequest.setDepartment(departmentService.findById(departmentId));
        newRequest.setRequestLines(requestLinesContainer.getRequestLines());
        newRequest.setAuthor(applicationUserService.getCurrentUser());
        requestService.save(newRequest);
        return "redirect:/requests";
    }

    @GetMapping("requests")
    public String showAllRequests(Model model){

        model.addAttribute("requests", requestService.findAll());
        return "allRequests";
    }

    @GetMapping("requests/all")
    public ModelAndView showAllRequestLines(){

        ModelAndView mav = new ModelAndView();
        RequestLinesContainer container = new RequestLinesContainer();
        container.setRequestLines(requestService.findAll().stream()
                                    .flatMap(request -> request.getRequestLines().stream())
                                    .collect(Collectors.toList()));

        mav.addObject("container",container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @GetMapping("requests/{id}")
    public ModelAndView showRequest(@PathVariable Long id){

        ModelAndView mav = new ModelAndView();
        RequestLinesContainer container = new RequestLinesContainer();
        container.setRequestLines(requestService.findById(id).getRequestLines());
        mav.addObject("container",container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @GetMapping("requests/my")
    public ModelAndView showMyRequests(){
        ModelAndView mav = new ModelAndView();
        ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        RequestLinesContainer container = new RequestLinesContainer();
        container.setRequestLines(requestService.findByAuthor(currentUser).stream()
                                    .flatMap(request -> request.getRequestLines().stream())
                                    .collect(Collectors.toList()));
        mav.addObject("container", container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

}
