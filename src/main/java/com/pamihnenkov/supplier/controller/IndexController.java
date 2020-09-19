package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import com.pamihnenkov.supplier.model.Uom;
import com.pamihnenkov.supplier.service.UomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final UomService uomService;

    @Autowired
    public IndexController(UomService uomService) {
        this.uomService = uomService;
    }

    @RequestMapping({"/","","index"})
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        Request newRequest = new Request();
        List<RequestLine> requestLineList = new ArrayList<>();
        mav.addObject("newRequest", newRequest);
        mav.addObject("requestLines",requestLineList);
        mav.addObject("uoms", uomService.findAll());
        mav.setViewName("newRequestForm");
        return mav;
    }
}
