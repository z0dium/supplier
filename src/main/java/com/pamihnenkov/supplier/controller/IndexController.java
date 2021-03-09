package com.pamihnenkov.supplier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping({"/app","index"})
    public String index(){
        return "index";
    }


}
