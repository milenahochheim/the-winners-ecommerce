package com.dev.ecommerce;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.catalina.connector.Request;
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
        produto.setAvaliacao(0.5);
        produto.setNome("nome teste");
        produto.setDescricao("descricao teste");
        produto.setNomeImagem(file.getOriginalFilename());
        produto.setPreco(19.90);
        produto.setQuantidade(5);

        when(produtoRepository.saveAndFlush(any(Produto.class))).thenReturn(produto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/admin/produtos/salvar")
        .file(file)
        .param("produto", "produto");
        
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(view().name("admin/produtos/cadastro"))
            .andExpect(model().attributeExists("produto"));
    }

    @Test
    void testRetornarImagem_ImagemExistente() throws Exception {
        String imageName = "existing_image.jpg";
        byte[] imageData = "test image data".getBytes();
        Path tempDir = Paths.get("src/test/java/com/dev/ecommerce/imgs/existing_image.jpg");
        produtoController.setCaminhoImagens(tempDir.getParent().toString()+"/");
        Files.write(tempDir, imageData);

        when(produtoRepository.findById(any())).thenReturn(Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/produtos/mostrarImagem/{imagem}", imageName);//java.nio.file.NoSuchFileException: C:\Users\MATHEU~1\AppData\Local\Temp\existing_image.jpg

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageData));

        Files.deleteIfExists(tempDir);
    }

}
