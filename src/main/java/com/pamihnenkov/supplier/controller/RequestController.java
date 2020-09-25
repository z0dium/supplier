package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUserService;
import com.pamihnenkov.supplier.service.RequestService;
import com.pamihnenkov.supplier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class RequestController {

    private final RequestService requestService;
    private final ApplicationUserService applicationUserService;


    @Autowired
    public RequestController(RequestService requestService, UserService userService, ApplicationUserService applicationUserService) {
        this.requestService = requestService;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("requests/create")
    public String showCreateForm(Model model) {

        RequestLinesContainer requestLinesContainer = new RequestLinesContainer();
        requestLinesContainer.getRequestLines().add(new RequestLine());
        model.addAttribute("requestLinesContainer", requestLinesContainer);
        return "newRequest";
    }

    @PostMapping("requests/save")
    public String createRequest(@ModelAttribute RequestLinesContainer requestLinesContainer, Model model) {
        Request newRequest = new Request();
        newRequest.setDate(new Date());
        newRequest.setRequestLines(requestLinesContainer.getRequestLines());
        ApplicationUser currentUser = (ApplicationUser) applicationUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        newRequest.setAuthor(currentUser);
        requestService.save(newRequest);
        return "redirect:/requests";
    }

    @GetMapping("requests")
    public String showAllRequests(Model model){

        model.addAttribute("requests", requestService.findAll());
        return "allRequests";
    }

}
