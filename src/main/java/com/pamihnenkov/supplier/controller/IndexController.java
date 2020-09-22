package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import com.pamihnenkov.supplier.model.RequestLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping({"/","","index"})
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        Request newRequest = new Request();
        List<RequestLine> requestLineList = new ArrayList<>();
        mav.addObject("newRequest", newRequest);
        mav.addObject("requestLines",requestLineList);
        mav.setViewName("newRequestForm");
        return mav;
    }
}
