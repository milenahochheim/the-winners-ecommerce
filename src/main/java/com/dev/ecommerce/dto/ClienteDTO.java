package com.dev.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;

import com.dev.ecommerce.model.Cliente;
import com.dev.ecommerce.model.Endereco;

public abstract class ClienteDTO implements Serializable {

    private List<Endereco> enderecosDTOs;
    
    //DTOs
    private String cep;

    private String cep_1;

    private String cep_2;

    private String rua;

    private String rua_1;

    private String rua_2;

    private String numero;

    private String numero_1;

    private String numero_2;

    private String bairro;

    private String bairro_1;

    private String bairro_2;

    private String cidade;

    private String cidade_1;

    private String cidade_2;

    private String uf;

    private String uf_1;

    private String uf_2;

    private String complemento;

    private String complemento_1;

    public ClienteDTO() {
        super();
    }

    private String complemento_2;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Endereco> getEnderecosDTOs() {
        if(enderecosDTOs == null){
            enderecosDTOs = new java.util.ArrayList<>();
        }
        Endereco endereco = obterEnderecoDTO(cep, rua, numero, bairro, cidade, uf, complemento);
        Endereco endereco_1 = obterEnderecoDTO(cep_1, rua_1, numero_1, bairro_1, cidade_1, uf_1, complemento_1);
        Endereco endereco_2 = obterEnderecoDTO(cep_2, rua_2, numero_2, bairro_2, cidade_2, uf_2, complemento_2);

        if(endereco != null){
            enderecosDTOs.add(endereco);
        }
        if(endereco_1 != null ){
            enderecosDTOs.add(endereco_1);
        }
        if(endereco_2 != null){
            enderecosDTOs.add(endereco_2);
        }
        return enderecosDTOs;
    }

    

    public void setEnderecosDTOs(List<Endereco> enderecosDTOs) {
        this.enderecosDTOs = enderecosDTOs;
    }

    public String getCep_1() {
        return cep_1;
    }

    public void setCep_1(String cep_1) {
        this.cep_1 = cep_1;
    }

    public String getCep_2() {
        return cep_2;
    }

    public void setCep_2(String cep_2) {
        this.cep_2 = cep_2;
    }

    public String getRua_1() {
        return rua_1;
    }

    public void setRua_1(String rua_1) {
        this.rua_1 = rua_1;
    }

    public String getRua_2() {
        return rua_2;
    }

    public void setRua_2(String rua_2) {
        this.rua_2 = rua_2;
    }

    public String getNumero_1() {
        return numero_1;
    }

    public void setNumero_1(String numero_1) {
        this.numero_1 = numero_1;
    }

    public String getNumero_2() {
        return numero_2;
    }

    public void setNumero_2(String numero_2) {
        this.numero_2 = numero_2;
    }

    public String getBairro_1() {
        return bairro_1;
    }

    public void setBairro_1(String bairro_1) {
        this.bairro_1 = bairro_1;
    }

    public String getBairro_2() {
        return bairro_2;
    }

    public void setBairro_2(String bairro_2) {
        this.bairro_2 = bairro_2;
    }

    public String getCidade_1() {
        return cidade_1;
    }

    public void setCidade_1(String cidade_1) {
        this.cidade_1 = cidade_1;
    }

    public String getCidade_2() {
        return cidade_2;
    }

    public void setCidade_2(String cidade_2) {
        this.cidade_2 = cidade_2;
    }

    public String getUf_1() {
        return uf_1;
    }

    public void setUf_1(String uf_1) {
        this.uf_1 = uf_1;
    }

    public String getUf_2() {
        return uf_2;
    }

    public void setUf_2(String uf_2) {
        this.uf_2 = uf_2;
    }

    public String getComplemento_1() {
        return complemento_1;
    }

    public void setComplemento_1(String complemento_1) {
        this.complemento_1 = complemento_1;
    }

    public String getComplemento_2() {
        return complemento_2;
    }

    public void setComplemento_2(String complemento_2) {
        this.complemento_2 = complemento_2;
    }

    private Endereco obterEnderecoDTO(String cep, String rua, String numero, String bairro, String cidade, String uf, String complemento) {
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setComplemento(complemento);
        return endereco;
    }
}
