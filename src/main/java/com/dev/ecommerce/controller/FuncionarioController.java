package com.dev.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.model.Funcionario;
import com.dev.ecommerce.repository.CargoRepository;
import com.dev.ecommerce.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

    static List<String> cargo = null;
    static {
        cargo = new ArrayList<>();
        cargo.add("Administrador");
        cargo.add("Estoquista");
    }

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/admin/funcionarios/cadastro")
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("admin/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listaCargos", cargoRepository.findAll());
        return mv;
    }

    // @GetMapping("/admin/funcionarios/cadastro")
    // public ModelAndView cadastrar(Model model) {
    // ModelAndView mv = new ModelAndView("admin/funcionarios/cadastro");
    // model.addAttribute("cargo", cargo);
    // return mv;
    // }

    @GetMapping("/admin/funcionarios/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("admin/funcionarios/listar");
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listaCargos", cargoRepository.findAll());

        return mv;
    }

    @GetMapping("/admin/funcionarios/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, Model model) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        return cadastrar(funcionario.get());
    }

    @GetMapping("/admin/funcionarios/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        funcionarioRepository.delete(funcionario.get());
        return listar();
    }

    @PostMapping("/admin/funcionarios/salvar")
    public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {

        // System.out.println(result.getAllErrors());
        if (result.hasErrors()) {
            return cadastrar(funcionario);
        }
        funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
        funcionarioRepository.saveAndFlush(funcionario);

        return cadastrar(new Funcionario());
    }

    @PostMapping("/admin/funcionarios/{id}")
    public ModelAndView atualizarStatus(@PathVariable("id") Long id) {
      
        Funcionario funcionario = funcionarioRepository.findById(id).get();
       
        if (funcionario.isStatus() == false) {

            funcionario.setStatus(true);
            funcionarioRepository.saveAndFlush(funcionario);
        } else {
            funcionario.setStatus(false);

           funcionarioRepository.saveAndFlush(funcionario);
        }
        funcionarioRepository.saveAndFlush(funcionario);
        return  listar();


    }
}
