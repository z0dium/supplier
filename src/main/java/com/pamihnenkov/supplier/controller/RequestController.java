package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class RequestController {

    private final RequestService requestService;
    private final ApplicationUserService applicationUserService;
    private final DepartmentService departmentService;

    @Autowired
    public RequestController(RequestService requestService, ApplicationUserService applicationUserService, DepartmentService departmentService) {
        this.requestService = requestService;
        this.applicationUserService = applicationUserService;
        this.departmentService = departmentService;
    }

    @GetMapping("requests/create")
    public String showCreateForm(Model model) {

        Request request = new Request();
    //    request.setDepartment(new Department());
        request.setRequestLines(List.of(new RequestLine()));   //1 empty string needed for better vision on fronted
        Iterable<Department> departments = (departmentService.findAll());  // список компаний для селектов
        model.addAttribute(request);
        model.addAttribute("departments",departments);

        return "newRequest";
    }

    @PostMapping("requests/save")
    public String createRequest(@ModelAttribute Request request) {

        request.setDepartment(departmentService.findById(request.getDepartment().getId()));
        request.setAuthor(applicationUserService.getCurrentUser());
        request.setDate(new Date());
        requestService.save(request);
        return "redirect:/app/requests";
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
        Request request = requestService.findById(id);
        mav.addObject("container",new RequestLinesContainer(request.getRequestLines()));
        mav.addObject("request", request);
        mav.setViewName("allRequestsLines");
        return mav;
    }

    @Secured("ROLE_SUPPLIER")
    @PostMapping("requests/{id}/update")
    public String updateRequest(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Request request = requestService.findById(longId);
        request.setSupplier(applicationUserService.getCurrentUser());
        requestService.save(request);
        return "redirect:/app/supplier/checking";
    }

    @GetMapping("requests/my")
    public ModelAndView showMyRequests(){
        ModelAndView mav = new ModelAndView();
        RequestLinesContainer container = new RequestLinesContainer();
        container.setRequestLines(requestService.findByAuthor(applicationUserService.getCurrentUser()).stream()
                                    .flatMap(request -> request.getRequestLines().stream())
                                    .collect(Collectors.toList()));
        mav.addObject("container", container);
        mav.setViewName("allRequestsLines");
        return mav;
    }

}
