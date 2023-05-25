package com.dev.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.controller.CargoController;
import com.dev.ecommerce.model.Cargo;
import com.dev.ecommerce.repository.CargoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CargoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CargoRepository cargoRepository;

    private CargoController cargoController;

    private RequestBuilder requestBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cargoController = new CargoController();
        mockMvc = MockMvcBuilders.standaloneSetup(cargoController).build();
    }

    @Test
    void testCadastrar() throws Exception {
        mockMvc.perform(get("/admin/cargos/cadastrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/cargos/cadastro"))
                .andExpect(model().attributeExists("cargo"));
    }

    @Test
    void testListar() throws Exception {
        List<Cargo> cargos = Arrays.asList(new Cargo(), new Cargo());
        when(cargoRepository.findAll()).thenReturn(cargos);

        mockMvc.perform(requestBuilder.get("/admin/cargos/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/cargos/lista"))
                .andExpect(model().attribute("listacargos", cargos));
    }

    @Test
    void testEditar() throws Exception {
        Long id = 1L;
        Optional<Cargo> cargoOptional = Optional.of(new Cargo());
        when(cargoRepository.findById(id)).thenReturn(cargoOptional);

        mockMvc.perform(get("/admin/cargos/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/cargos/cadastro"))
                .andExpect(model().attributeExists("cargo"));

        verify(cargoRepository, times(1)).findById(id);
    }

    @Test
    void testRemover() throws Exception {
        Long id = 1L;
        Optional<Cargo> cargoOptional = Optional.of(new Cargo());
        when(cargoRepository.findById(id)).thenReturn(cargoOptional);

        mockMvc.perform(get("/admin/cargos/remover/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/cargos/lista"));

        verify(cargoRepository, times(1)).delete(cargoOptional.get());
    }

    @Test
    void testSalvar() throws Exception {
        Cargo cargo = new Cargo();

        mockMvc.perform(post("/admin/cargos/salvar")
                .flashAttr("cargo", cargo))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/cargos/cadastro"))
                .andExpect(model().attributeExists("cargo"));

        verify(cargoRepository, times(1)).saveAndFlush(any(Cargo.class));
    }
}
