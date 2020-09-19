package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.RequestLinesContainer;
import com.pamihnenkov.supplier.service.RequestService;
import com.pamihnenkov.supplier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    private final RequestService requestService;
    private final UserService userService; //testing purpose


    @Autowired
    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;

        this.userService = userService;
    }

    @GetMapping("request/create")
    public String showCreateForm(Model model) {

        RequestLinesContainer requestLinesContainer = new RequestLinesContainer();
        requestLinesContainer.getRequestLines().add(new RequestLine());
        model.addAttribute("requestLinesContainer", requestLinesContainer);
        return "newRequest";
    }

    @PostMapping("request/save")
    public String createRequest(@ModelAttribute RequestLinesContainer requestLinesContainer, Model model) {
        Request newRequest = new Request();
        newRequest.setDate(System.currentTimeMillis());
        newRequest.setRequestLines(requestLinesContainer.getRequestLines());
        newRequest.setAuthor(userService.findById(1L)); //testing purpose. Should be changed to CurrentUser
        requestService.save(newRequest);
        model.addAttribute("requests", requestService.findAll());
        return "allRequests";
    }

    @GetMapping("request")
    public String showAllRequests(Model model){

        model.addAttribute("requests", requestService.findAll());
        return "allRequests";
    }

}
