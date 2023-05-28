package com.dev.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dev.ecommerce.dto.ProdutoDTO;
import java.util.ArrayList;

@Entity
@Table(name = "produtos")
public class Produto extends ProdutoDTO {
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Imagem.class, cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    private String nome;

    private String descricao;

    private int quantidade = 0;

    private Double preco = 0.;

    private Double avaliacao;

    private boolean status = true;

    public Produto() {
        super();
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNomeImagem(int i) {
        return imagens.get(i).getNome();
    }

    public void setNomeImagem(String nomeImagem, int i) {
        imagens.get(i).setNome(nomeImagem);
    }

    @Override
    public String getNomeImagem() {
        if(!imagens.isEmpty()){
            return imagens.get(imagens.size() - 1).getNome();
        }
        return null;
    }

    @Override
    public void setNomeImagem(String nomeImagem) {
        if(imagens == null){
            imagens = new ArrayList<>();
        }
        imagens.add(new Imagem());
        imagens.get(imagens.size() - 1).setNome(nomeImagem);
    }

    public void addImagem(Imagem imagem) {
        if(imagens == null){
            imagens = new ArrayList<>();
        }
        if(imagem.getCaminho() == null || imagem.getCaminho().isEmpty()){
            throw  new RuntimeException("caminho da imagem não informado");
        }
        if(imagem.getNome() == null || imagem.getNome().isEmpty()){
            throw  new RuntimeException("nome da imagem não informado");
        }
        if(imagem.getProduto() == null || imagem.getProduto().getId() == null){
            throw  new RuntimeException("produto da imagem não informado");
        }
        imagens.add(imagem);
    }

    public void remove(Imagem imagem) {
        if(imagens == null || imagens.isEmpty()){
            return;
        }
        imagens.remove(imagem);
    }
}
