package com.pamihnenkov.supplier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    public String getLoginView(){
        return "login";
    }
}
