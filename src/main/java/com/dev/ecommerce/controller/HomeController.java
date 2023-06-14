package com.dev.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String acessarHome() {
        return "admin/home";
    }

    @GetMapping("/estoquista")
    public String acessarEstoquista() {
        return "estoquista/home";
    }
}
