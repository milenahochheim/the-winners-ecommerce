package com.dev.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginClienteController {
    @GetMapping("/cliente/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("/cliente/login");

        return mv;
    }
}
