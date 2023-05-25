package com.dev.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.controller.ProdutoController;
import com.dev.ecommerce.repository.ProdutoRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProdutoRepository produtoRepository;

    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        produtoController = new ProdutoController();
        // produtoController.setProdutoRepository(produtoRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void testRetornarImagem() throws Exception {
        byte[] imageData = "test image data".getBytes();
        Path tempFile = Files.createTempFile("test", ".jpg");
        Files.write(tempFile, imageData);

        String imageName = tempFile.getFileName().toString();

        when(produtoRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/produtos/mostrarImagem/{imagem}", imageName))
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageData));

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testRemover() throws Exception {
        Long id = 1L;
        Optional<Produto> produtoOptional = Optional.of(new Produto());
        when(produtoRepository.findById(id)).thenReturn(produtoOptional);

        mockMvc.perform(get("/admin/produtos/remover/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("listar"));

        verify(produtoRepository, times(1)).delete(produtoOptional.get());
    }

    @Test
    void testSalvar() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);

        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image data".getBytes());

        mockMvc.perform(fileUpload("/admin/produtos/salvar")
                .file(file)
                .param("nome", "Test Produto")
                .param("descricao", "Test Description")
                .param("preco", "10.00"))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastrar"))
                .andExpect(model().attributeExists("produto"));

        verify(produtoRepository, times(1)).saveAndFlush(any(Produto.class));
    }
}
