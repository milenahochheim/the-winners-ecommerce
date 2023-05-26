package com.dev.ecommerce;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.dev.ecommerce.controller.ProdutoController;
import com.dev.ecommerce.model.Produto;
import com.dev.ecommerce.repository.ProdutoRepository;

class ProdutoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void testSalvar_ProdutoComArquivo() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image data".getBytes());
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoRepository.saveAndFlush(any(Produto.class))).thenReturn(produto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/admin/produtos/salvar")
        .file(file)
        .param("nome", "Test Produto")
        .param("descricao", "Test Description")
        .param("preco", "10.00");
        
        mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(view().name("admin/produtos/cadastro"))
        .andExpect(model().attributeExists("produto"));
                

        verify(produtoRepository, times(1)).saveAndFlush(any(Produto.class));
        verify(produtoRepository, times(1)).saveAndFlush(produto);
        // Verifique outras ações necessárias, como a escrita do arquivo.
    }

    @Test
    void testRetornarImagem_ImagemExistente() throws Exception {
        String imageName = "existing_image.jpg";
        byte[] imageData = "test image data".getBytes();
        Path tempFile = Files.createTempFile("test", ".jpg");
        Files.write(tempFile, imageData);

        when(produtoRepository.findById(any())).thenReturn(Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/produtos/mostrarImagem/{imagem}", imageName);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageData));//The method content() is undefined for the type ProdutoControllerTestJava(67108964)

        Files.deleteIfExists(tempFile);
    }
}
