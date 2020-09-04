package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping({"/","","index"})
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("newRequest", new Request());
        mav.setViewName("newRequestForm");
        return mav;
    }
}
