package com.dev.ecommerce.dto;

import java.io.Serializable;

public abstract class ProdutoDTO implements Serializable {

    private String nomeImagem;

    public abstract String getNomeImagem();

    public abstract void setNomeImagem(String nomeImagem);

    public ProdutoDTO(){
        super();
    }
}
