package com.dev.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.model.Cargo;
import com.dev.ecommerce.repository.CargoRepository;
import com.dev.ecommerce.repository.FuncionarioRepository;

@Controller
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping("/cargos/cadastro")
    public ModelAndView cadastrar(Cargo cargo) {
        ModelAndView mv = new ModelAndView("admin/cargos/cadastro");
        mv.addObject("cargo", cargo);
        return mv;
    }

    @GetMapping("/cargos/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("admin/cargos/listar");
        mv.addObject("listaCargos", cargoRepository.findAll());
        return mv;
    }

    @GetMapping("/cargos/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);
        return cadastrar(cargo.get());
    }

    @GetMapping("/cargos/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);
        cargoRepository.delete(cargo.get());
        return listar();
    }

    @PostMapping("/cargos/salvar")
    public ModelAndView salvar(@Valid Cargo cargo, BindingResult result) {

        if (result.hasErrors()) {
            return cadastrar(cargo);
        }
        cargoRepository.saveAndFlush(cargo);

        return cadastrar(new Cargo());
    }
}
