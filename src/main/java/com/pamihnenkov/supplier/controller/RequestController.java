package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.model.commandObjects.Department.DepartmentIdAndNameCom;
import com.pamihnenkov.supplier.model.commandObjects.Request.AuthorAndDepartmentAndGoalCom;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.service.serviceInterfaces.ApplicationUserService;
import com.pamihnenkov.supplier.service.serviceInterfaces.DepartmentService;
import com.pamihnenkov.supplier.service.serviceInterfaces.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        request.setDepartment(new Department());
        request.setRequestLines(List.of(new RequestLine()));   //1 empty string needed for better vision on fronted


        Iterable<DepartmentIdAndNameCom> departments = departmentService.findByLeader(applicationUserService.getCurrentUser()).stream()
                                                    .map(DepartmentIdAndNameCom::new)
                                                    .collect(Collectors.toList());  // список компаний для селектов

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
        Request request = requestService.findById(id);
        AuthorAndDepartmentAndGoalCom metaRequest = new AuthorAndDepartmentAndGoalCom(request.getGoal(),
                                                                        request.getAuthor().getSurname() + ' ' + request.getAuthor().getName(),
                                                                              request.getDepartment().getName());
        container.setRequestLines(request.getRequestLines());
        mav.addObject("container",container);
        mav.addObject("request", metaRequest);
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
