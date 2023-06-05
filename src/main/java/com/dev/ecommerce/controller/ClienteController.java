package com.dev.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.model.Cliente;
import com.dev.ecommerce.repository.ClienteRepository;
import com.dev.ecommerce.repository.CompraRepository;
import com.dev.ecommerce.model.Compra;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("/cliente/cadastro")
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/cliente/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cadastrar(cliente.get());
    }

    @PostMapping("/cliente/salvar")
    public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {

        if (result.hasErrors()) {
            return cadastrar(cliente);
        }
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        cliente = clienteRepository.saveAndFlush(cliente); // obter id do cliente

        cliente.setEnderecos(cliente.getEnderecosDTOs());
        clienteRepository.save(cliente);

        return cadastrar(new Cliente());
    }

    @GetMapping("/cliente/perfil")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("cliente/perfil");
        mv.addObject("listaCliente", clienteRepository.findAll());
        mv.addObject("listaCompras", compraRepository.findAll());

        return mv;
    }

}
