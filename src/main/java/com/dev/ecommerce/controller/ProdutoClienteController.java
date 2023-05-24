package com.dev.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.model.Produto;
import com.dev.ecommerce.repository.ProdutoRepository;

@Controller
public class ProdutoClienteController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produto/{id}")
    public ModelAndView produtoPage(@PathVariable("id") Long id, Model model) {
        ModelAndView mv = new ModelAndView("/cliente/produto");
        Optional<Produto> produto = produtoRepository.findById(id);
        mv.addObject("prodid", produto.get());
        return mv;
    }
}
