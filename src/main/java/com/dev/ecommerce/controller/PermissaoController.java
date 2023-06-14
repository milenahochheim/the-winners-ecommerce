package com.dev.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.model.Permissao;
import com.dev.ecommerce.repository.CargoRepository;
import com.dev.ecommerce.repository.FuncionarioRepository;
import com.dev.ecommerce.repository.PermissaoRepository;

@Controller
public class PermissaoController {
    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/permissoes/cadastro")
    public ModelAndView cadastrar(Permissao permissao) {
        ModelAndView mv = new ModelAndView("admin/permissoes/cadastro");
        mv.addObject("permissao", permissao);
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listaCargos", cargoRepository.findAll());
        return mv;
    }

    @GetMapping("/permissoes/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("admin/permissoes/listar");
        mv.addObject("listaPermissoes", permissaoRepository.findAll());
        return mv;
    }

    @GetMapping("/permissoes/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        return cadastrar(permissao.get());
    }

    @GetMapping("/permissoes/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        permissaoRepository.delete(permissao.get());
        return listar();
    }

    @PostMapping("/permissoes/salvar")
    public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {

        if (result.hasErrors()) {
            return cadastrar(permissao);
        }
        permissaoRepository.saveAndFlush(permissao);

        return cadastrar(new Permissao());
    }

}
