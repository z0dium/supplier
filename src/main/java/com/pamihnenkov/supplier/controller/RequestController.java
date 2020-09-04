package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping("/addRequest")
    public String index(@ModelAttribute("newRequest") Request request){
        requestService.save(request);
        return "newForm";
    }

}
