package com.dev.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.repository.ItensCompraRepository;

@Controller
public class ItensCompraController {
    @Autowired
    private ItensCompraRepository itensCompraRepository;

    @GetMapping("itens/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("admin/itens/listar");
        mv.addObject("listaItensCompra", itensCompraRepository.findAll());

        return mv;
    }
}
