package com.dev.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import com.dev.ecommerce.model.Compra;
import com.dev.ecommerce.repository.CompraRepository;

@Controller
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("pedidos/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("admin/pedidos/listar");
        mv.addObject("listaCompras", compraRepository.findAll());

        return mv;
    }
}
