package com.example.produtos.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
/*Pessoa -> classe */
public class Produto {

/* atributos */

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int indentificador;

@Column(nullable = false)
private String nome;

@Column(nullable = false)
private String descricao;

@Column(nullable = false)
private String qualidade;

@Column(nullable = false)
private double preco;

@ManyToOne
@JoinColumn(name = "idCategoria",referencedColumnName = "id")
private Categoria categoria;

@ManyToOne
@JoinColumn(name = "idFornecedor",referencedColumnName = "id")
private Fornecedor fornecedor;


/*Construtor */
    public Produto(int indentificador, String nome, String descricao, String qualidade, double preco, Categoria categoria, Fornecedor fornecedor){
        

    /*inicializadores */
    this.indentificador = indentificador;
    this.nome = nome;
    this.descricao = descricao;
    this.qualidade = qualidade;
    this.preco = preco;
    this.categoria = categoria;
    this.fornecedor = fornecedor;




    }

    public Produto() {

    }


    public int getIndentificador(){
        return indentificador;   
    }
    public String getNome(){
        return nome;   
    }
    public String getDescricao(){
        return descricao;   
    }
    public String getQualidade(){
        return qualidade; 
    }
    public double getPreco(){
        return preco;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public Fornecedor getFornecedor(){
        return fornecedor;
    }

    /*Estou apenas criando os seters */
    public void setIdentificador(int identificador){
        this.indentificador = identificador;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setQualidade(String qualidade){
        this.qualidade = qualidade;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
        }
        public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor = fornecedor;

    }
}



/*void -> nao retorna */
/*static ->retorna */